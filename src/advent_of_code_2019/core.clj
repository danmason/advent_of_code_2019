(ns advent-of-code-2019.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn task1-calc-fuel [mass]
  (-> mass
      (/ 3)
      (int)
      (- 2)))

(defn task2-calc-fuel [original-mass]
  (loop [mass original-mass total-mass 0]
    (let [fuel (task1-calc-fuel mass)]
      (if (<= fuel 0)
        total-mass
        (recur fuel (+ total-mass fuel))))))

(defn day1 [task-fn]
    (->> (slurp (io/resource "day1.txt"))
         (string/split-lines)
         (map #(task-fn (Integer/parseInt %)))
         (reduce +)))

(def task1
  (day1 task1-calc-fuel))

(def task2
  (day1 task2-calc-fuel))
