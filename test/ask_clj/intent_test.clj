(ns ask-clj.intent-test
  (:require [clojure.pprint :as p]
            [ask-clj.intent :as i]
            [clojure.test :refer :all]))

(def intent-request {:request
                     {:type "IntentRequest"
                     :requestId "123abc"
                     :timeStamp "2020-09-28"
                     :dialogState "STARTED"
                     :locale "en-US"
                     :intent {:name "TestIntent"
                              :confirmationStatus "NONE"
                              :slots {:firstName {:name "firstName"
                                                  :value "John"
                                                  :confirmationStatus "CONFIRMED"
                                                  :resolutions {:resolutionsPerAuthority [{:authority ""
                                                                                           :status {:code "123"}
                                                                                           :values [{:value {:name "givenName"
                                                                                                             :id "givenName"}}]}]}}}}}})

(deftest get-intent
  (testing "Get intent from request."
    (let [intent (i/get-intent intent-request)]
      (is (= intent "TestIntent")))))

(deftest get-slots
  (testing "Get slots from request."
    (let [slots (i/get-slots intent-request)]
      (is (= slots {:firstName {:name "firstName"
                                :value "John"
                                :confirmationStatus "CONFIRMED"
                                :resolutions {:resolutionsPerAuthority [{:authority ""
                                                                         :status {:code "123"}
                                                                         :values [{:value {:name "givenName"
                                                                                           :id "givenName"}}]}]}}})))))

(deftest get-dialog-state
  (testing "Get dialog state from request."
    (let [dialogState (i/get-dialog-state intent-request)]
      (is (= dialogState "STARTED")))))

(deftest get-intent-confirmation-status
  (testing "Get intent confirmation status from request."
    (let [status (i/get-intent-confirmation-status intent-request)]
      (is (= status "NONE")))))

(deftest get-slot-confirmation-status
  (testing "Get slot confirmation status from request."
    (let [status (i/get-slot-confirmation-status intent-request "firstName")]
      (is (= status "CONFIRMED")))))

(deftest get-resolutions-per-authority
  (testing "Get resolutions per authority from request."
    (let [res (i/get-resolutions-per-authority intent-request "firstName")]
      (is (= res [{:authority ""
                   :status {:code "123"}
                   :values [{:value {:name "givenName"
                                     :id "givenName"}}]}])))))