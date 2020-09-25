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

(defn gen-display-template-text
  "HASHMAP: Generates the textContent for a display template."
  ([primary secondary tertiary]
   {:primaryText {:text primary
                  :type "PlainText"}
    :secondaryText {:text secondary
                    :type "PlainText"}
    :tertiaryText {:text tertiary
                   :type "PlainText"}})
  ([primary secondary]
   {:primaryText {:text primary
                  :type "PlainText"}
    :secondaryText {:text secondary
                    :type "PlainText"}})
  ([primary]
   {:primaryText {:text primary
                  :type "PlainText"}}))

(defn set-display-body-template-1
  "Adds a display body template to the response."
  ([res token bgImg title txt]
  (let [template {:type "Display.RenderTemplate"
                  :template {:type "BodyTemplate1"
                             :token token
                             :backgroundImage bgImg
                             :title title
                             :textContent txt
                             :backButton "HIDDEN"}}]
    (if (some? (:directives (:response @res)))
      (swap! res update-in [:response :directives] merge template)
      (swap! res assoc-in [:response :directives] (vector template)))))
  ([res token bgImg title txt btnVisability]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate1"
                              :token token
                              :backgroundImage bgImg
                              :title title
                              :textContent txt
                              :backButton btnVisability}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template))))))