(ns ask-clj.session
  (:require [cheshire.core :refer :all]))

; SESSION METHODS
(defn get-user-from-session
  "Returns the user object from a request."
  [req]
  (:user (:session (parse-string req true))))

(defn new-session?
  "Returns true if new session, else returns false."
  [req]
  (:new (:session (parse-string req true))))

(defn get-application-id
  "Returns the application ID."
  [req]
  (:applicationId (:application (:session (parse-string req true)))))

(defn get-session-id
  "Returns the session ID."
  [req]
  (:sessionId (:session (parse-string req true))))

(defn get-session-attrs
  "Returns a hashmap of session attributes."
  [req]
  (:attributes (:session (parse-string req true))))
