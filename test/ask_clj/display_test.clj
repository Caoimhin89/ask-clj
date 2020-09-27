(ns ask-clj.display-test
  (:require [clojure.pprint :as p]
            [clojure.test :refer :all]
            [ask-clj.display :as d]))

(deftest gen-display-template-text-content
  (testing "Generate textContent object for display templates."
    (let [textContent (d/gen-display-template-text "PRIMUS" "Secundus" "insignificante!")]
      (is (= textContent {:primaryText {:text "PRIMUS"
                                        :type "PlainText"}
                          :secondaryText {:text "Secundus"
                                          :type "PlainText"}
                          :tertiaryText {:text "insignificante!"
                                         :type "PlainText"}})))))

(deftest gen-image-source
  (testing "Generate source for display template image."
    (let [source (d/gen-image-source "https://test-img.jpg" "xl")]
      (is (= source {:url "https://test-img.jpg"
                     :size "X_LARGE"
                     :widthPixels 1920
                     :heightPixels 1280})))))

(deftest gen-display-template-image
  (testing "Generates a displayTemplate image."
    (let [src1 (d/gen-image-source "https://test-xs.jpg" "xs")
          src2 (d/gen-image-source "https://test-s.jpg" "s")
          src3 (d/gen-image-source "https://test-m.jpg" "m")]
      (let [img (d/gen-display-template-image "My description..." (vector src1 src2 src3))]
        (is (= img {:contentDescription "My description..."
                    :sources (vector src1 src2 src3)}))))))