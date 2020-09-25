(ns ask-clj.audio-player-test
  (:require [clojure.test :refer :all]
            [ask-clj.context :as ctx]
            [ask-clj.audio-player :as ap]))

(def basic-req "")
(def audio-req "{ \"version\": \"1.0\", \"session\": { \"new\": true, \"sessionId\": \"amzn1.echo-api.session.[unique-value-here]\", \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"attributes\": { \"key\": \"string value\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } } }, \"context\": { \"System\": { \"device\": { \"deviceId\": \"string\", \"supportedInterfaces\": { \"AudioPlayer\": {} } }, \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } }, \"person\": { \"personId\": \"amzn1.ask.person.[unique-value-here]\", \"accessToken\": \"Atza|BBBBBBB...\" }, \"unit\": { \"unitId\": \"amzn1.ask.unit.[unique-value-here]\", \"persistentUnitId\" : \"amzn1.alexa.unit.did.[unique-value-here]\" }, \"apiEndpoint\": \"https://api.amazonalexa.com\", \"apiAccessToken\": \"AxThk;afjaofjaojflajfEJGALJ\" }, \"AudioPlayer\": { \"playerActivity\": \"PLAYING\", \"token\": \"audioplayer-token\", \"offsetInMilliseconds\": 1000 } }, \"request\": {} }")
(def video-req "")

(def audio-player (ctx/get-audio-player audio-req))

(deftest get-token
  (testing "Get audioplayer token."
    (let [token (ap/get-token audio-player)]
      (is (= token "audioplayer-token")))))

(deftest get-offset-in-milliseconds
  (testing "Get offsetInMilliseconds."
    (let [offset (ap/get-offset-in-milliseconds audio-player)]
      (is (= offset 1000)))))

(deftest get-player-activity
  (testing "Get audioplayer activity."
    (let [activity (ap/get-player-activity audio-player)]
      (is (= activity "PLAYING")))))