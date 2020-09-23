(ns ask-clj.core)

; SESSION METHODS
(defn get-user-from-session
  "Returns the user object from a request."
  [req]
  (:user (:session req)))

(defn new-session?
  "Returns true if new session, else returns false."
  [req]
  (:new (:session req)))

(defn get-application-id
  "Returns the application ID."
  [req]
  (:applicationId (:application (:session req))))

(defn get-session-id
  "Returns the session ID."
  [req]
  (:sessionId (:session req)))

(defn get-sess-attrs
  "Returns a hashmap of session attributes."
  [req]
  (:attributes (:session req)))

; CONTEXT METHODS
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

; AUDIO PLAYER METHODS
(defn get-audioplayer-token
  "String: An opaque token that represents the audio stream described 
   by this AudioPlayer object. You provide this token when sending the 
   Play directive. This is only included in the AudioPlayer object when 
   your skill was the skill most recently playing audio on the device."
  [ap]
  (:token ap))

(defn get-offset-in-milliseconds
  "Identifies a track's offset in milliseconds at the time the request
   was sent. This is 0 if the track is at the beginning. This is only 
   included in the AudioPlayer object when your skill was the skill most 
   recently playing audio on the device."
  [ap]
  (:offsetInMilliseconds ap))

(defn get-player-activity
  "String: Indicates the last known state of audio playback.
   IDLE: Nothing was playing, no enqueued items.
   PAUSED: Stream was paused.
   PLAYING: Stream was playing.
   BUFFER_UNDERRUN: Buffer underrun.
   FINISHED: Stream was finished playing.
   STOPPED: Stream was interrupted."
  [ap]
  (:playerActivity ap))


