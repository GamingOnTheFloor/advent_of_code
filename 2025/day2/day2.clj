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


(defn get-divisors [num] (->> (range 1 (/ (+ num 1) 2)) (filter (fn [x] (= 0 (rem num x))))))

(defn part2-invalid? [num] (let [len (count (str num))
                                 divisors (get-divisors len)
                                 splits (map (fn [divisor] (partition divisor (str num))) divisors)]
                             (some false? (map (fn [split] (not= 1 (count (set split)))) splits))))

(defn sum-part2-invalids-in-range [[start end]] (->> (range start (+ 1 end)) (reduce (fn [sum range] (if (part2-invalid? range) (+ sum range) sum)) 0)))

(def part2-answer (apply + (map sum-part2-invalids-in-range ranges)))
part2-answer