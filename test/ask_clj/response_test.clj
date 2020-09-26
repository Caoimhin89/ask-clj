(ns ask-clj.response-test
  (:require [clojure.pprint :as p]
            [clojure.test :refer :all]
            [ask-clj.response :as r]
            [ask-clj.display :as d]))

(def basic-req "")
(def audio-req "{ \"version\": \"1.0\", \"session\": { \"new\": true, \"sessionId\": \"amzn1.echo-api.session.[unique-value-here]\", \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"attributes\": { \"key\": \"string value\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } } }, \"context\": { \"System\": { \"device\": { \"deviceId\": \"string\", \"supportedInterfaces\": { \"AudioPlayer\": {} } }, \"application\": { \"applicationId\": \"amzn1.ask.skill.[unique-value-here]\" }, \"user\": { \"userId\": \"amzn1.ask.account.[unique-value-here]\", \"accessToken\": \"Atza|AAAAAAAA...\", \"permissions\": { \"consentToken\": \"ZZZZZZZ...\" } }, \"person\": { \"personId\": \"amzn1.ask.person.[unique-value-here]\", \"accessToken\": \"Atza|BBBBBBB...\" }, \"unit\": { \"unitId\": \"amzn1.ask.unit.[unique-value-here]\", \"persistentUnitId\" : \"amzn1.alexa.unit.did.[unique-value-here]\" }, \"apiEndpoint\": \"https://api.amazonalexa.com\", \"apiAccessToken\": \"AxThk;afjaofjaojflajfEJGALJ\" }, \"AudioPlayer\": { \"playerActivity\": \"PLAYING\", \"token\": \"audioplayer-token\", \"offsetInMilliseconds\": 0 } }, \"request\": {} }")
(def video-req "")

(deftest set-app-version
  (testing "Set application version."
    (let [response (atom {})]
      (r/set-app-version response "1.0")
      (is (= @response {:version "1.0"})))))

(deftest set-session-attribute
  (testing "Set session attribute."
    (let [response (atom {})]
      (r/set-session-attribute response "key" "value")
      (r/set-session-attribute response "foo" "bar")
      (r/set-session-attribute response "obj" {:foo "bar"
                                               :baz "buz"})
      (is (= @response {:sessionAttributes {:key "value"
                                            :foo "bar"
                                            :obj {:foo "bar"
                                                  :baz "buz"}}})))))

(deftest set-speech-txt
  (testing "Set speech text."
    (let [response (atom {})]
      (r/set-speech-txt response "Hi there" true)
      (is (= (:response @response) {:outputSpeech {:type "PlainText"
                                                   :text "Hi there"
                                                   :playBehavior "ENQUEUE"}
                                    :shouldSessionEnd true})))))

(deftest set-speech-ssml
  (testing "Set speech text."
    (let [response (atom {})]
      (r/set-speech-ssml response "<speak>Hi there</speak>" false)
      (is (= (:response @response) {:outputSpeech {:type "SSML"
                                                   :ssml "<speak>Hi there</speak>"
                                                   :playBehavior "ENQUEUE"}
                                    :shouldSessionEnd false})))))

(deftest set-reprompt-txt
  (testing "Set reprompt text as plain text."
    (let [response (atom {})]
      (r/set-reprompt-txt response "Would you like to play again?" false)
      (is (= (:response @response) {:reprompt {:outputSpeech {:text "Would you like to play again?"
                                                              :type "PlainText"
                                                              :playBehavior "ENQUEUE"}}
                                    :shouldSessionEnd false})))))

(deftest set-reprompt-ssml
  (testing "Set reprompt text as ssml."
    (let [response (atom {})]
      (r/set-reprompt-ssml response "<speak>Would you like to play again?</speak>" false)
      (is (= (:response @response) {:reprompt {:outputSpeech {:ssml "<speak>Would you like to play again?</speak>"
                                                              :type "SSML"
                                                              :playBehavior "ENQUEUE"}}
                                    :shouldSessionEnd false})))))

(deftest set-simple-card
  (testing "Set simple card."
    (let [response (atom {})]
      (r/set-simple-card response "My Title" "Here is some text.")
      (is (= (:response @response) {:card {:type "Simple"
                                           :title "My Title"
                                           :content "Here is some text."}})))))

(deftest set-standard-card-with-photos
  (testing "Set standard card with photos."
    (let [response (atom {})]
      (r/set-standard-card response "My Title" "Here is some text." "https://small-image.jpg" "https://big-image.jpg")
      (is (= (:response @response) {:card {:type "Standard"
                                           :title "My Title"
                                           :text "Here is some text."
                                           :image {:smallImageUrl "https://small-image.jpg"
                                                   :largeImageUrl "https://big-image.jpg"}}})))))

(deftest set-standard-card-no-photos
  (testing "Set standard card without photos."
    (let [response (atom {})]
      (r/set-standard-card response "My Title" "Here is some text.")
      (is (= (:response @response) {:card {:type "Standard"
                                           :title "My Title"
                                           :text "Here is some text."}})))))

(deftest set-session-end-true
  (testing "Set shouldSessionEnd to true."
    (let [response (atom {})]
      (r/set-session-end response true)
      (is (= (:response @response) {:shouldSessionEnd true})))))

(deftest set-session-end-false
  (testing "Set shouldSessionEnd to false."
    (let [response (atom {})]
      (r/set-session-end response false)
      (is (= (:response @response) {:shouldSessionEnd false})))))

(deftest set-link-account-card
  (testing "Set a Link Account card."
    (let [response (atom {})]
      (r/set-link-account-card response)
      (is (= (:response @response) {:card {:type "LinkAccount"}})))))

(deftest set-display-template
  (testing "Set display template in response."
    (let [temp (d/gen-display-body-template-1 
                "myToken" 
                (d/gen-display-template-image
                 "My description..."
                 (vector (d/gen-image-source "https://my-image.jpg" "s" 720 480)
                         (d/gen-image-source "https://my-image2.jpg" "m" 960 640)
                         (d/gen-image-source "https://image.jpg" "l")))
                "My Title"
                (d/gen-display-template-text "PRIMUS!" "Secundus!" "tertiary!"))
          response (atom {})]
      (r/set-display-template response temp)
      (p/pprint @response)
      (p/pprint {:response {:directives (vector temp)}})
      (is (= @response {:response {:directives (vector temp)}})))))