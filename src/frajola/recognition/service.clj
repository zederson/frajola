(ns frajola.recognition.service
  (:use [frajola.config :as config])
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api-key
  ; (config/env "IBM_API_KEY"))

(def url
  (str "https://gateway-a.watsonplatform.net/visual-recognition/api/v3/classify?api_key=" api-key "&version=2016-05-20"))

(defn post-image
  [image-path]
  (let [response (client/post url {:body (clojure.java.io/input-stream path-file)})]
    (json/read-str (:body response) :key-fn keyword)))

(defn send-file
  [path-file]
  (let [body (post-image path-file)]

  )
)



; (def image-path "/Users/edersonlima/Downloads/passarinho.jpg")
;
; (http/post url {:body (clojure.java.io/input-stream image-path)})


; (def body "{\n    \"custom_classes\": 0,\n    \"images\": [\n        {\n            \"classifiers\": [\n                {\n                    \"classes\": [\n                        {\n                            \"class\": \"English sparrow\",\n                            \"score\": 0.81,\n                            \"type_hierarchy\": \"/animal/bird/English sparrow\"\n                        },\n                        {\n                            \"class\": \"bird\",\n                            \"score\": 0.969\n                        },\n                        {\n                            \"class\": \"animal\",\n                            \"score\": 0.969\n                        },\n                        {\n \"class\": \"sparrow\",\n                            \"score\": 0.769,\n                            \"type_hierarchy\": \"/animal/bird/sparrow\"\n                        },\n                        {\n                            \"class\": \"white-crowned sparrow\",\n                            \"score\": 0.553,\n                            \"type_hierarchy\": \"/animal/bird/finch/New World sparrow/white-crowned sparrow\"\n                        },\n                        {\n                            \"class\": \"New World sparrow\",\n                            \"score\": 0.587\n                        },\n                        {\n                            \"class\": \"finch\",\n                            \"score\": 0.595\n                        },\n                        {\n                            \"class\": \"hedge sparrow\",\n                    \"score\": 0.53,\n                            \"type_hierarchy\": \"/animal/bird/hedge sparrow\"\n                        },\n                        {\n                            \"class\": \"light brown color\",\n                            \"score\": 0.694\n                        },\n                        {\n                            \"class\": \"reddish orange color\",\n                            \"score\": 0.538\n                        }\n                    ],\n                    \"classifier_id\": \"default\",\n                    \"name\": \"default\"\n                }\n            ]\n        }\n    ],\n    \"images_processed\": 1\n}\n" )
;
;
; (def xx (:classes (first (-> (first (-> (json/read-str (:body response) :key-fn keyword) :images)) :classifiers))))
;
; (filter
;   (fn[clazz]
;     (= (:class clazz) "bird" )
;   )
; xx)
;
;
; (filter (fn[clazz] (= (:class clazz) "bird" ) ) xx)
