(ns ask-clj.response
  (:require [cheshire.core :refer :all]
            [clojure.spec.alpha :as s]))

(defn set-speech-txt
  "Sets the output speech as plain text."
  [res, txt]
  (swap! res update-in [:response :outputSpeech] assoc 
         :text txt 
         :type "PlainText"
         :playBehavior "ENQUEUE"))

(defn set-speech-ssml
  "Sets the output speech as SSML."
  [res, saml]
  (swap! res update-in [:response :outputSpeech] assoc
         :ssml saml
         :type "SSML"
         :playBehavior "ENQUEUE"))