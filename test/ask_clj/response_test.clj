(ns ask-clj.response-test
  (:require [clojure.test :refer :all]
            [ask-clj.response :as r]))

(def basic-req "")
(def audio-req "{ \"version\": \"1.0\", \"session\": { \"new\": true, \"sessionId\": \"amzn1.echo-api.session.[unique-value-here]\", \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"attributes\": { \"key\": \"string value\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } } }, \"context\": { \"System\": { \"device\": { \"deviceId\": \"string\", \"supportedInterfaces\": { \"AudioPlayer\": {} } }, \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } }, \"person\": { \"personId\": \"amzn1.ask.person.[unique-value-here]\", \"accessToken\": \"Atza|BBBBBBB...\" }, \"unit\": { \"unitId\": \"amzn1.ask.unit.[unique-value-here]\", \"persistentUnitId\" : \"amzn1.alexa.unit.did.[unique-value-here]\" }, \"apiEndpoint\": \"https://api.amazonalexa.com\", \"apiAccessToken\": \"AxThk;afjaofjaojflajfEJGALJ\" }, \"AudioPlayer\": { \"playerActivity\": \"PLAYING\", \"token\": \"audioplayer-token\", \"offsetInMilliseconds\": 0 } }, \"request\": {} }")
(def video-req "")

(deftest set-speech-txt
  (testing "Set speech text."
    (let [response (atom {})]
      (r/set-speech-txt response "Hi there")
      (is (= (:outputSpeech (:response @response)) {:type "PlainText"
                                                    :text "Hi there"
                                                    :playBehavior "ENQUEUE"})))))

(deftest set-speech-ssml
  (testing "Set speech text."
    (let [response (atom {})]
      (r/set-speech-ssml response "<speak>Hi there</speak>")
      (is (= (:outputSpeech (:response @response)) {:type "SSML"
                                                    :ssml "<speak>Hi there</speak>"
                                                    :playBehavior "ENQUEUE"})))))

(deftest set-reprompt-txt
  (testing "Set reprompt text as plain text."
    (let [response (atom {})]
      (r/set-reprompt-txt response "Would you like to play again?")
      (is (= (:response @response) {:reprompt {:outputSpeech {:text "Would you like to play again?"
                                                              :type "PlainText"
                                                              :playBehavior "ENQUEUE"}}})))))

(deftest set-simple-card
  (testing "Set simple card."
    (let [response (atom {})]
      (r/set-simple-card response "My Title" "Here is some text.")
      (is (= (:response @response) {:card {:title "My Title"
                                          :content "Here is some text."}})))))