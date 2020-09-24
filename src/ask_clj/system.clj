(ns ask-clj.system)

(defn get-api-access-token 
  "STRING: token that can be used to access Alexa-specific APIs. This token encapsulates:
            - Any permissions the user has consented to, such as permission to access the user's address with 
            the Device Location API.
            - Access to other Alexa-specific APIs, such as the Progressive Response API"
  [sys]
  (:apiAccessToken sys))

(defn get-api-endpoint 
  "STRING: A string that references the correct base URI to refer to by region, 
            for use with APIs such as the Device Location API and Progressive Response API."
  [sys]
  (:apiEndpoint sys))

(defn get-application-id 
  "STRING: The appliation ID for your skill."
  [sys]
  (:applicationId (:application sys)))

(defn get-device 
  "HASHMAP: An object providing information about the device used to send the request. 
            The device object contains both deviceId and supportedInterfaces properties"
  [sys]
  (:device sys))

(defn get-unit 
  "An object that represents a logical construct organizing actors (such as people and organizations) 
   and resources (such as devices and skills) that interact with Alexa systems."
  [sys]
  (:unit sys))