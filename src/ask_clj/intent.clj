(ns ask-clj.intent
  (:require [cheshire.core :refer :all]))

(defn get-intent
  "STRING: Returns request intent."
  [req]
  (:name (:intent (:request req))))

(defn  get-slots
  "VECTOR of HASHMAPS: Returns a vector of slot objects."
  [req]
  (:slots (:intent (:request req))))

(defn get-dialog-state
  "STRING: Returns the current dialog state.
   - STARTED: User invoked the intent that has a dialog.
   - IN_PROGRESS: Dialog is in progress.
   - COMPLETED: All required slots have values. Only exists when dialog delegated."
  [req]
  (:dialogState (:request req)))

(defn get-intent-confirmation-status
  "STRING: Returns the confirmations status for an intent.
   - NONE
   - CONFIRMED
   - DENIED"
  [req]
  (:confirmationStatus (:intent (:request req))))

(defn get-slot-confirmation-status
  "STRING: Returns the current confirmation status for a slot."
  [req slot]
  (:confirmationStatus (get (:slots (:intent (:request req))) (keyword slot))))

(defn get-resolutions-per-authority
  "VECTOR of HASHMAPS: representing each possible authority for entity resolution. 
   An authority represents the source for the data provided for the slot. 
   For a custom slot type, the authority is the slot type you defined.
   - req: Full json request
   - slot: Name of the slot for which resolutions are required.
   RETURNS: {authority: String, status: {code: String}, values: [{value: {name: String, id: String}}]}"
  [req slot]
  (:resolutionsPerAuthority (:resolutions (get (:slots (:intent (:request req))) (keyword slot)))))