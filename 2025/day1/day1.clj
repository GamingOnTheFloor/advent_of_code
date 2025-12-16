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

;; (->> (reductions + start (rotation-to-int input)) (map (fn [x] (mod x 100))) last)

;; (defn rotation-to-int-mod-100 [input-list]
;;   (->> input-list (map (fn [rotation] (let [number (Integer/parseInt (apply str (rest rotation)))
;;                                             direction (first rotation)]
;;                                         (if (= direction \L)
;;                                           (-> (- number) (mod 100))
;;                                           number))))))


;; (def pos (apply + start (filter (fn [x] (> x 0)) (rotation-to-int input))))
;; (def neg (apply + (filter (fn [x] (< x 0)) (rotation-to-int input))))
;; (quot pos 100)
;; (quot neg 100)
;; (mod pos 100)
;; (- (mod neg 100) 100) ;; subtracting 100 to get an equivalent value, but negative

;; (+ (quot pos 100) ;; Number of times it went to the right
;;    (Math/abs (quot neg 100)) ;; Number of times around it went to the left
;;    ()) ;; Plus 1 because -88 and 86 


;; check answer before, compare to answer after, 
;; is after on the other side of 0 than before?
;; Don't forget to check quot of all answers as well
;; (reduce (fn [[location times-crossed-0] next-val] (let [next-location (rem (+ location next-val) 100) ;; location and next-location mod 100
;;                                                         times-around-with-current-move (Math/abs (quot next-val 100))
;;                                                         crossed? (if (or (and (neg? location) (pos? next-location)) (and (pos? location) (neg? next-location)) (zero? next-location)) 1 0)]
;;                                                     [next-location (+ times-around-with-current-move crossed? times-crossed-0)])) [50 0] [10 -10 10 -10 10 -10 10 -10 10 -10])

;; [10 -10 10 -10 10 -10 10 -10 10 -10] [50 0]
;; [-50 50 -50 50] [50 2]
;; [1050] [0 11]

;; (def part2-answer (->> (reduce (fn [[location times-crossed-0] next-val] (let [next-location (rem (+ location next-val) 100) ;; location and next-location mod 100
;;                                                                                times-around-with-current-move (Math/abs (quot next-val 100))
;;                                                                                crossed? (if (or (and (neg? location) (pos? next-location)) (and (pos? location) (neg? next-location)) (zero? next-location)) 1 0)]
;;                                                                            [next-location (+ times-around-with-current-move crossed? times-crossed-0)])) [50 0] (rotation-to-int input))
;;                        second))
;; part2-answer

;; (reduce (fn [x y] (mod (+ x y) 100)) [1 100 150])

;; (apply + start (rotation-to-int-mod-100 input))

;; (apply (fn [x y] (+ x y)) [1 1 1 1])

;; (rem -174 100)

;; It's ugly but it works
(defn walk [start steps] (reduce (fn [[start times-crossed-0] y] 
                                   (let [new-start (rem (+ start y) 100)]
                                        [new-start (if (zero? new-start) (+ times-crossed-0 1) times-crossed-0)])) [start 0] (repeat (Math/abs steps) (if (pos? steps) 1 -1))))

;; (walk 50 1050)

(def part2-answer (second (reduce (fn [[start times-crossed-0] y] (let [walk (walk start y)] [(first walk) (+ (second walk) times-crossed-0)])) [start 0] (rotation-to-int input))))
part2-answer