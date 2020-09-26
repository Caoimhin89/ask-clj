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

(defn gen-image-source
  "HASHMAP: Generates the source object for a display template image object."
  [url size width height]
  {:url url
   :size size
   :widthPixels width
   :heightPixels height})

(defn gen-display-template-image
  "HASHMAP: Generates an image object for a display template.
   Accepts a text description and a vector of image objects."
  [desc images]
  {:contentDescription desc
   :sources images})

(defn set-display-body-template-1
  "Adds display BodyTemplate1 to the response."
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

(defn set-display-body-template-2
  "Adds display BodyTemplate2 to the response."
  ([res token bgImg img title txt]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate2"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton "HIDDEN"}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template)))))
  ([res token bgImg img title txt btnVisability]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate2"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton btnVisability}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template))))))

(defn set-display-body-template-3
  "Adds display BodyTemplate3 to the response."
  ([res token bgImg img title txt]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate3"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton "HIDDEN"}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template)))))
  ([res token bgImg img title txt btnVisability]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate3"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton btnVisability}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template))))))

(defn set-display-body-template-6
  "Adds display BodyTemplate6 to the response."
  ([res token bgImg img txt]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate6"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :textContent txt
                              :backButton "HIDDEN"}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template)))))
  ([res token bgImg img txt btnVisability]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate6"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :textContent txt
                              :backButton btnVisability}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template))))))

(defn set-display-body-template-7
  "Adds display BodyTemplate7 to the response."
  ([res token bgImg img bgTxt imgTxt]
   (let [template {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate7"
                              :token token
                              :backgroundImage {:contentDescription bgTxt
                                                :sources (vector {:url bgImg})}
                              :image {:contentDescription imgTxt
                                      :sources (vector {:url img})}
                              :backButton "VISIBLE"}}]
     (if (some? (:directives (:response @res)))
       (swap! res update-in [:response :directives] merge template)
       (swap! res assoc-in [:response :directives] (vector template))))))

(defn gen-list-item
  "Creates a listItem for a listTemplate.
   Args:
   - token: String
   - img: Image object
   - txt: String"
  [token img txt]
  {:token token
   :image img
   :textContent txt})