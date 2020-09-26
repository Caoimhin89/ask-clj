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