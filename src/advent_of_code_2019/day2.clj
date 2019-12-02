(ns advent-of-code-2019.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn read-file []
  (-> (slurp (io/resource "day2.txt"))
      (string/split-lines)
      (first)
      (string/split #",")
      ((fn [ints] (mapv #(Integer/parseInt %) ints)))
      (assoc 1 12)
      (assoc 2 2)))

(defn task1-process [intcode]
  (loop [codes intcode pos 0]
    (let [op-set (take 4 (drop pos codes))]
      (case (first op-set)
        1 (recur (assoc codes (nth op-set 3) (+ (nth codes (nth op-set 1))
                                                (nth codes (nth op-set 2))))
                 (+ pos 4))
        2 (recur (assoc codes (nth op-set 3) (* (nth codes (nth op-set 1))
                                                (nth codes (nth op-set 2))))
                 (+ pos 4))
        99 codes))))

(def task1
  (task1-process (read-file)))
