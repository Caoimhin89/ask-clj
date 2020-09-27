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

(def size-map (hash-map :xs {:w 480 :h 320 :txt "X_SMALL"}
                        :s {:w 720 :h 480 :txt "SMALL"}
                        :m {:w 960 :h 640 :txt "MEDIUM"}
                        :l {:w 1200 :h 800 :txt "LARGE"}
                        :xl {:w 1920 :h 1280 :txt "X_LARGE"}))

(defn gen-image-source
  "HASHMAP: Generates the source object for a display template image object.
   Supplying url & size only will auto-fill width & height based on best practices.
   Can manually override these defaults by supplying your own width & height in pixels.
   - url: String
   - size: xs (480x320) | s (720x480) | m (960x640) | l (1200x800) | xl (1920x1280)
   -- OR --
   - url: String
   - size: String: xs | s | m | l | xl
   - widthPixels: Integer
   - heightPixels: Integer"
  ([url size]
   (let [s (keyword size)]
     {:url url
      :size (:txt (get size-map s))
      :widthPixels (:w (get size-map s))
      :heightPixels (:h (get size-map s))}))
  ([url size width height]
  (let [s (keyword size)]
    {:url url
     :size (:txt (get size-map s))
     :widthPixels width
     :heightPixels height})))

(defn gen-display-template-image
  "HASHMAP: Generates an image object for a display template.
   Accepts the  following arguments:
   - desc: String: text description of the image
   - images: Vector: a vector of displayImage source objects."
  [desc images]
  {:contentDescription desc
   :sources images})

(defn gen-display-body-template-1
  "HASHMAP: Adds display BodyTemplate1 to the response."
  ([token bgImg title txt]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate1"
                              :token token
                              :backgroundImage bgImg
                              :title title
                              :textContent txt
                              :backButton "HIDDEN"}})
  ([token bgImg title txt btnVisability]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate1"
                              :token token
                              :backgroundImage bgImg
                              :title title
                              :textContent txt
                              :backButton btnVisability}}))

(defn gen-display-body-template-2
  "HASHMAP: Adds display BodyTemplate2 to the response."
  ([token bgImg img title txt]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate2"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton "HIDDEN"}})
  ([token bgImg img title txt btnVisability]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate2"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton btnVisability}}))

(defn gen-display-body-template-3
  "HASHMAP: Adds display BodyTemplate3 to the response."
  ([token bgImg img title txt]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate3"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton "HIDDEN"}})
  ([token bgImg img title txt btnVisability]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate3"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :title title
                              :textContent txt
                              :backButton btnVisability}}))

(defn gen-display-body-template-6
  "HASHMAP: Adds display BodyTemplate6 to the response."
  ([token bgImg img txt]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate6"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :textContent txt
                              :backButton "HIDDEN"}})
  ([token bgImg img txt btnVisability]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate6"
                              :token token
                              :backgroundImage bgImg
                              :image img
                              :textContent txt
                              :backButton btnVisability}}))

(defn gen-display-body-template-7
  "HASHMAP: Creates a display BodyTemplate7."
  ([token bgImg img bgTxt imgTxt]
   {:type "Display.RenderTemplate"
                   :template {:type "BodyTemplate7"
                              :token token
                              :backgroundImage {:contentDescription bgTxt
                                                :sources (vector {:url bgImg})}
                              :image {:contentDescription imgTxt
                                      :sources (vector {:url img})}
                              :backButton "VISIBLE"}}))

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

(defn gen-display-list-template-1
  "HASHMAP: Creates a display ListTemplate1."
  ([token bgImg title items btnVisability]
   {:type "Display.RenderTemplate"
    :template {:type "ListTemplate1"
               :token  token
               :backButton (if btnVisability
                             "VISIBLE"
                             "HIDDEN")
               :backgroundImage bgImg
               :title  title
               :listItems items}})
  ([token bgImg title items]
   {:type "Display.RenderTemplate"
    :template {:type "ListTemplate1"
               :token  token
               :backButton "VISIBLE"
               :backgroundImage bgImg
               :title title
               :listItems items}}))