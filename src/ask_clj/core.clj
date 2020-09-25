(ns ask-clj.core
  (:require [ask-clj.response :as res]
            [ask-clj.session :as sess]
            [ask-clj.context :as ctx]
            [ask-clj.system :as sys]
            [ask-clj.audio-player :as audio-player]
            [cheshire.core :refer :all]))

(def response (atom {}))

(defn get-intent
  [req]
  (:type (:request (parse-string req true))))
