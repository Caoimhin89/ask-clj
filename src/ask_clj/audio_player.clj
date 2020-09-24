(ns ask-clj.audio-player
  (:require [cheshire.core :refer :all]))

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


