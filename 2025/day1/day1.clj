(def file "day1/input.txt")

(def input (clojure.string/split-lines (slurp file)))

(def start 50)
(def target 0)

(defn rotation-to-int [input-list] 
  (->> input-list (map (fn [rotation] (let [number (Integer/parseInt (apply str (rest rotation)))
                                            direction (first rotation)]
                                        (if (= direction \L)
                                          (- number)
                                          number))))))

(def part1-answer (->> (reductions + start (rotation-to-int input)) (map (fn [x] (mod x 100))) (filter (fn [x] (= x target))) count))
part1-answer