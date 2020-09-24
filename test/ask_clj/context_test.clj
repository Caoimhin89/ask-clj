(ns ask-clj.context-test
  (:require [clojure.test :refer :all]
            [ask-clj.context :as ctx]))

(def basic-req "")
(def audio-req "{ \"version\": \"1.0\", \"session\": { \"new\": true, \"sessionId\": \"amzn1.echo-api.session.[unique-value-here]\", \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"attributes\": { \"key\": \"string value\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } } }, \"context\": { \"System\": { \"device\": { \"deviceId\": \"string\", \"supportedInterfaces\": { \"AudioPlayer\": {} } }, \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } }, \"person\": { \"personId\": \"amzn1.ask.person.[unique-value-here]\", \"accessToken\": \"Atza|BBBBBBB...\" }, \"unit\": { \"unitId\": \"amzn1.ask.unit.[unique-value-here]\", \"persistentUnitId\" : \"amzn1.alexa.unit.did.[unique-value-here]\" }, \"apiEndpoint\": \"https://api.amazonalexa.com\", \"apiAccessToken\": \"AxThk;afjaofjaojflajfEJGALJ\" }, \"AudioPlayer\": { \"playerActivity\": \"PLAYING\", \"token\": \"audioplayer-token\", \"offsetInMilliseconds\": 0 } }, \"request\": {} }")
(def video-req "")

(deftest get-user-from-context
  (testing "Get the user object from the context."
    (let [user (ctx/get-user-from-context audio-req)]
      (is (= user {:userId "amzn1.ask.account.[unique-value-here]"
                   :accessToken "Atza|AAAAAAAA..."
                   :permissions {:consentToken "ZZZZZZZ..."}})))))

(deftest get-supported-interfaces
  (testing "Get list of supported interfaces from context."
    (let [interfaces (ctx/get-supported-interfaces audio-req)]
      (is (= interfaces [:AudioPlayer])))))

(deftest get-api-access-token
  (testing "Get api access token from context."
    (let [apiToken (ctx/get-api-access-token audio-req)]
      (is (= apiToken "AxThk;afjaofjaojflajfEJGALJ")))))

(deftest get-apl-doc
  (testing "Get the APL document from context."
    (let [aplDoc (ctx/get-apl-doc audio-req)]
      (is (= aplDoc nil)))))

(deftest get-audio-player
  (testing "Get the AudioPlayer object from context."
    (let [audio-player (ctx/get-audio-player audio-req)]
      (is (= audio-player {:playerActivity "PLAYING", 
                           :token "audioplayer-token", 
                           :offsetInMilliseconds 0})))))