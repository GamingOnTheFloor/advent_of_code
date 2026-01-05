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

(defn char-to-int [char] (-> char str Integer/parseInt))

(defn largest-twelve-digits [string-of-digits] (->> (drop 12 string-of-digits)
                                                     (reduce (fn [[a1 b2 c3 d4 e5 f6 g7 h8 i9 j10 k11 l12] next-digit]
                                                               (let [digit (Integer/parseInt (str next-digit))
                                                                     o1 (if (> b2 a1) b2 a1)
                                                                     p2  (if (and (= o1  b2 ) (not= a1  b2  )) c3    (if (> c3    b2 ) c3    b2))
                                                                     q3  (if (and (= p2  c3 ) (not= b2  c3  )) d4    (if (> d4    c3 ) d4    c3))
                                                                     r4  (if (and (= q3  d4 ) (not= c3  d4  )) e5    (if (> e5    d4 ) e5    d4))
                                                                     s5  (if (and (= r4  e5 ) (not= d4  e5  )) f6    (if (> f6    e5 ) f6    e5))
                                                                     t6  (if (and (= s5  f6 ) (not= e5  f6  )) g7    (if (> g7    f6 ) g7    f6))
                                                                     u7  (if (and (= t6  g7 ) (not= f6  g7  )) h8    (if (> h8    g7 ) h8    g7))
                                                                     v8  (if (and (= u7  h8 ) (not= g7  h8  )) i9    (if (> i9    h8 ) i9    h8))
                                                                     w9  (if (and (= v8  i9 ) (not= h8  i9  )) j10   (if (> j10   i9 ) j10   i9))
                                                                     x10 (if (and (= w9  j10) (not= i9  j10 )) k11   (if (> k11   j10) k11   j10))
                                                                     y11 (if (and (= x10 k11) (not= j10 k11 )) l12   (if (> l12   k11) l12   k11))
                                                                     z12 (if (and (= y11 l12) (not= k11 l12 )) digit (if (> digit l12) digit l12))]
                                                                 [o1 p2 q3 r4 s5 t6 u7 v8 w9 x10 y11 z12]))
                                                             (map char-to-int (take 12 string-of-digits)))))

(map largest-twelve-digits ["123456789123456789" "987654321987654321" "159159159159159159159159159195" "951951951951951951951951951951" "1111111111111111111119" "123456789123" "1298129812981298129812" "99999999911111119911" "99999999991111911111"])

(largest-twelve-digits "99999999911111119911")

(def part2-answer (->> input (map largest-twelve-digits) (map (fn [array] (Long/parseLong (apply str array)))) (apply +)))
part2-answer