(def file "day3/input.txt")

(def input (clojure.string/split-lines (slurp file)))

(defn largest-two-digits [string-of-digits] (->> (drop 2 string-of-digits)
                                                 (reduce (fn [[largest next] rest-of-digits]
                                                           (let [digit (Integer/parseInt (str rest-of-digits))
                                                                 y (if (> next largest) next largest)
                                                                 x (if (and (= y next) (not= largest next)) digit (if (> digit next) digit next))]
                                                             [y x])) 
                                                         [(-> (first string-of-digits) str Integer/parseInt) (-> (second string-of-digits) str Integer/parseInt)])))

(Integer/parseInt (str \1))


(largest-two-digits "123456789")
(map largest-two-digits ["123456789" "987654321" "159159159" "951951951" "1111119" "12" "129812"])
(def part1-answer (->> input (map largest-two-digits) (map (fn [[x y]] (Integer/parseInt (str x y)))) (apply +)))
part1-answer