(ns ask-clj.display
  (:require [cheshire.core :refer :all]
            [clojure.spec.alpha :as s]))

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

(defn gen-display-body-template-1
  "HASHMAP: Adds display BodyTemplate1 to the response."
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

(defn gen-display-body-template-2
  "HASHMAP: Adds display BodyTemplate2 to the response."
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

(defn gen-display-body-template-3
  "HASHMAP: Adds display BodyTemplate3 to the response."
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

(defn gen-display-body-template-6
  "HASHMAP: Adds display BodyTemplate6 to the response."
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

(defn gen-display-body-template-7
  "HASHMAP: Adds display BodyTemplate7 to the response."
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