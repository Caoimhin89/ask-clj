(ns ask-clj.session-test
  (:require [clojure.test :refer :all]
            [ask-clj.core :refer :all]
            [ask-clj.session :as session]))

(def basic-req "")
(def audio-req "{ \"version\": \"1.0\", \"session\": { \"new\": true, \"sessionId\": \"amzn1.echo-api.session.[unique-value-here]\", \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"attributes\": { \"key\": \"string value\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } } }, \"context\": { \"System\": { \"device\": { \"deviceId\": \"string\", \"supportedInterfaces\": { \"AudioPlayer\": {} } }, \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } }, \"person\": { \"personId\": \"amzn1.ask.person.[unique-value-here]\", \"accessToken\": \"Atza|BBBBBBB...\" }, \"unit\": { \"unitId\": \"amzn1.ask.unit.[unique-value-here]\", \"persistentUnitId\" : \"amzn1.alexa.unit.did.[unique-value-here]\" }, \"apiEndpoint\": \"https://api.amazonalexa.com\", \"apiAccessToken\": \"AxThk...\" }, \"AudioPlayer\": { \"playerActivity\": \"PLAYING\", \"token\": \"audioplayer-token\", \"offsetInMilliseconds\": 0 } }, \"request\": {} }")
(def video-req "")

(deftest get-user-from-session
  (testing "Get User From Session"
    (let [user (session/get-user-from-session audio-req)]
      (is (= user {:userId "amzn1.ask.account.[unique-value-here]",
                                                      :accessToken "Atza|AAAAAAAA...",
                                                      :permissions {:consentToken "ZZZZZZZ..."}})))))

(deftest new-session?
  (testing "Check if session is new"
    (let [newSes? (session/new-session? audio-req)]
      (is (= newSes? true)))))

(deftest get-application-Id
  (testing "Get applicationId from session."
    (let [appId (session/get-application-id audio-req)]
      (is (= appId "amzn1.ask.skill.[unique-value-here]")))))

(deftest get-session-id
  (testing "Get sessionId from session."
    (let [sesId (session/get-session-id audio-req)]
      (is (= sesId "amzn1.echo-api.session.[unique-value-here]")))))

(deftest get-session-attrs
  (testing "Get session attributes."
    (let [attrs (session/get-session-attrs audio-req)]
      (is (= attrs {:key "string value"})))))


