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

(deftest set-display-body-template-1-no-prior-directives
  (testing "Set Display BodyTemplate1 no with prior directives."
    (let [response (atom  {})]
      (d/gen-display-body-template-1 response
                                     "token"
                                     "https://background-image.jpg"
                                     "My Title"
                                     "lorem ipsum dolar sit amet...")
      (p/pprint @response)
      (is (= @response {:response {:directives (vector
                                                {:type "Display.RenderTemplate"
                                                 :template {:type "BodyTemplate1"
                                                            :token "token"
                                                            :backgroundImage "https://background-image.jpg"
                                                            :title "My Title"
                                                            :textContent "lorem ipsum dolar sit amet..."
                                                            :backButton "HIDDEN"}})}})))))