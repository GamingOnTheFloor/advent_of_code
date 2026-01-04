(def file "day2/input.txt")

(def input (clojure.string/split (slurp file) #","))

;; (def input ["28-45"])


(def ranges (->> input 
                 (map (fn [str] (clojure.string/split (clojure.string/trim str) #"-"))) 
                 (map (fn [[start end]] [(Long/parseUnsignedLong start) (Long/parseUnsignedLong end)]))))

ranges

(defn invalid? [num] (let [len (count (str num))
                           double-num (split-at (/ len 2) (str num))]
                       (= (get double-num 0) (get double-num 1))))

(defn sum-invalids-in-range [[start end]] (->> (range start (+ 1 end)) (reduce (fn [sum range] (if (invalid? range) (+ sum range) sum)) 0)))

(def part1-answer (apply + (map sum-invalids-in-range ranges)))
part1-answer
