(ns ask-clj.context
  (:require [cheshire.core :refer :all]))

(defn get-supported-interfaces
  "Returns seq of all supported interfaces."
  [req]
  (keys (:supportedInterfaces (:device (:System (:context (parse-string req true)))))))

(defn get-user-from-context
  "Returns user object as hashmap from context."
  [req]
  (:user (:System (:context (parse-string req true)))))

(defn get-api-access-token
  "Returns api access token from context."
  [req]
  (:apiAccessToken (:System (:context (parse-string req true)))))

(defn get-apl-doc
  "Returns the APL document from context."
  [req]
  (:Alexa.Presentation.APL (:context (parse-string req true))))

(defn get-audio-player
  "Returns AudioPlayer object from context."
  [req]
  (:AudioPlayer (:context (parse-string req true))))

(defn get-viewport
  "Returns the viewport object from the context."
  [req]
  (:Viewport (:context (parse-string req true))))

(defn get-viewports
  "Returns the Viewports array from the context"
  [req]
  (:Viewports (:context (parse-string req true))))
