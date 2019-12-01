(ns advent-of-code-2019.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn task1-calc-fuel [mass]
  (- (int (/ mass 3)) 2))

(defn task2-calc-fuel [original-mass]
  (loop [mass original-mass total-mass 0]
    (let [fuel (task1-calc-fuel mass)]
      (if (<= fuel 0)
        total-mass
        (recur fuel (+ total-mass fuel))))))

(defn apply-calc [task-fn]
    (->> (slurp (io/resource "day1.txt"))
         (string/split-lines)
         (map #(task-fn (Integer/parseInt %)))
         (reduce +)))

(def task1
  (apply-calc task1-calc-fuel))

(def task2
  (apply-calc task2-calc-fuel))
