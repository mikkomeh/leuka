(ns leuka.core
  (:gen-class)
  (:require [java-time :as jt]))
(import '(java.time.temporal IsoFields))

(defn- today []
  (jt/local-date))

(defn- day-of-week [date]
  (.getValue (jt/day-of-week date)))

(defn- week-of-year [date]
  (.get date IsoFields/WEEK_OF_WEEK_BASED_YEAR))

(defn- pull-ups-per-weekday [day-of-week]
  (let [pull-up-map {1 1, 2 2, 3 3, 4 4, 5 5, 6 0, 7 0}]
    (get pull-up-map day-of-week)))

(defn- pull-ups [date]
  (+ (pull-ups-per-weekday (day-of-week date)) (* (- (week-of-year date) 1) 5)))

(defn -main [& args]
  (println (str "Leukoja tänään: " (pull-ups (today)))))
