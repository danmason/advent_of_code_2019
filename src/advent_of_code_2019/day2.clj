(ns advent-of-code-2019.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn read-file []
  (-> (slurp (io/resource "day2.txt"))
      (#(clojure.edn/read-string (str  "[" % "]")))))

(defn process-intcodes [intcodes x y]
  (loop [codes (assoc intcodes 1 x 2 y)
         pos 0]
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
  (let [intcodes (read-file)
        processed-intcodes (process-intcodes intcodes 12 2)]
    (first processed-intcodes)))

(def task2
  (let [intcodes (read-file)]
    (first (for [noun (range 100)
                 verb (range 100)
                 :when (= 19690720
                          (first (process-intcodes intcodes noun verb)))]
             (+ (* 100 noun) verb)))))
