(ns ask-clj.context
  (:require [cheshire.core :refer :all]))

(defn get-supported-interfaces
  "Returns seq of all supported interfaces."
  [req]
  (keys (:supportedInterfaces (:device (:System (:context req))))))

(defn get-user-from-context
  "Returns user object as hashmap from context."
  [req]
  (:user (:System (:context req))))

(defn get-api-access-token
  "Returns api access token from context."
  [req]
  (:apiAccessToken (:System (:context req))))

(defn get-apl-doc
  "Returns the APL document from context."
  [req]
  (:Alexa.Presentation.APL (:context req)))

(defn get-audio-player
  "Returns AudioPlayer object from context."
  [req]
  (:AudioPlayer (:context req)))

(defn get-viewport
  "Returns the viewport object from the context."
  [req]
  (:Viewport (:context req)))

(defn get-viewports
  "Returns the Viewports array from the context"
  [req]
  (:Viewports (:context req)))
