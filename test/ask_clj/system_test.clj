(ns ask-clj.system-test
  (:require [clojure.test :refer :all]
            [ask-clj.context :as ctx]
            [ask-clj.system :as sys]))

(def basic-req "")
(def audio-req "{ \"version\": \"1.0\", \"session\": { \"new\": true, \"sessionId\": \"amzn1.echo-api.session.[unique-value-here]\", \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"attributes\": { \"key\": \"string value\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } } }, \"context\": { \"System\": { \"device\": { \"deviceId\": \"string\", \"supportedInterfaces\": { \"AudioPlayer\": {} } }, \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } }, \"person\": { \"personId\": \"amzn1.ask.person.[unique-value-here]\", \"accessToken\": \"Atza|BBBBBBB...\" }, \"unit\": { \"unitId\": \"amzn1.ask.unit.[unique-value-here]\", \"persistentUnitId\" : \"amzn1.alexa.unit.did.[unique-value-here]\" }, \"apiEndpoint\": \"https://api.amazonalexa.com\", \"apiAccessToken\": \"AxThk;afjaofjaojflajfEJGALJ\" }, \"AudioPlayer\": { \"playerActivity\": \"PLAYING\", \"token\": \"audioplayer-token\", \"offsetInMilliseconds\": 0 } }, \"request\": {} }")
(def video-req "")

(def system (ctx/get-system-object audio-req))

(deftest get-api-access-token
  (testing "Get API Access Token from System."
    (let [apiAccessToken (sys/get-api-access-token system)]
      (is (= apiAccessToken "AxThk;afjaofjaojflajfEJGALJ")))))

(deftest get-api-endpoint
  (testing "Get API Endpoint from System."
    (let [apiEndpoint (sys/get-api-endpoint system)]
      (is (= apiEndpoint "https://api.amazonalexa.com")))))

(deftest get-application-id
  (testing "Get applicationId from System."
    (let [appId (sys/get-application-id system)]
      (is (= appId "amzn1.ask.skill.[unique-value-here]")))))

(deftest get-device
  (testing "Get devices object from System."
    (let [device (sys/get-device system)]
      (is (= device {:deviceId "string",
                     :supportedInterfaces {:AudioPlayer {}}})))))