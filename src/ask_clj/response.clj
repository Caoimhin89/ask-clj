(ns ask-clj.response
  (:require [cheshire.core :refer :all]
            [clojure.spec.alpha :as s]))

(defn set-app-version
  "Sets version property in response."
  [res v]
  (swap! res assoc :version v))

(defn set-session-end
  "Sets shouldSessionEnd boolean."
  [res x]
  (swap! res update :response assoc :shouldSessionEnd x))

(defn set-session-attribute
  "Set session attribute."
  [res k v]
  (swap! res update-in [:sessionAttributes] assoc (keyword k) v))

(defn set-session-end
  "Sets shouldSessionEnd boolean."
  [res x]
  (swap! res update :response assoc :shouldSessionEnd x))

(defn set-speech-txt
  "Sets the output speech as plain text."
  [res txt end?]
  (set-session-end res end?)
  (swap! res update-in [:response :outputSpeech] assoc 
         :text txt 
         :type "PlainText"
         :playBehavior "ENQUEUE"))

(defn set-speech-ssml
  "Sets the output speech as SSML."
  [res saml end?]
  (set-session-end res end?)
  (swap! res update-in [:response :outputSpeech] assoc
         :ssml saml
         :type "SSML"
         :playBehavior "ENQUEUE"))

(defn set-reprompt-txt
  "Sets the reprompt speech as plain text."
  [res txt end?]
  (set-session-end res end?)
  (swap! res update-in [:response :reprompt :outputSpeech] assoc
         :text txt
         :type "PlainText"
         :playBehavior "ENQUEUE"))

(defn set-reprompt-ssml
  "Sets the reprompt speech as ssml."
  [res ssml end?]
  (set-session-end res end?)
  (swap! res update-in [:response :reprompt :outputSpeech] assoc
         :ssml ssml
         :type "SSML"
         :playBehavior "ENQUEUE"))

(defn set-simple-card
  [res title content]
  (swap! res update-in [:response :card] assoc 
         :type "Simple"
         :title title 
         :content content))

(defn set-standard-card
  "Set a standard card."
  ([res title text]
   (swap! res update-in [:response :card] assoc 
           :type "Standard"
           :title title
           :text text))
  ([res title text sm lg]
    (swap! res update-in [:response :card] assoc
           :type "Standard"
           :title title
           :text text
           :image {:smallImageUrl sm
                   :largeImageUrl lg})))

(defn set-link-account-card
  "A card that displays a link to an authorization URI that the user can use to 
   link their Alexa account with a user in another system."
  [res]
  (swap! res update-in [:response :card] assoc :type "LinkAccount"))

(defn set-permissions-card
  "A card that asks the customer for consent to obtain specific customer 
   information, such as Alexa lists or address information.
   Args:
   - response: atom
   - permissions: vector of Permission strings"
  [res perms]
  (swap! res update-in [:response :card] assoc 
         :type "AskForPermissionsConsent"
         :permissions perms))

(defn set-display-template
  "Adds a displayTemplate to the response."
  [res temp]
  (if (some? (:directives (:response @res)))
    (swap! res update-in [:response :directives] merge temp)
    (swap! res assoc-in [:response :directives] (vector temp))))