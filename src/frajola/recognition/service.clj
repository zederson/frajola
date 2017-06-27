(ns frajola.recognition.service
  (:use [frajola.config :as config])
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api-key
  (config/env "IBM_API_KEY"))

(def url
  (str "https://gateway-a.watsonplatform.net/visual-recognition/api/v3/classify?api_key=" api-key "&version=2016-05-20"))

(def type-image "bird")

(def score 0.6)

(defn post-image
  [image-path]
  (let [response (client/post url {:body (clojure.java.io/input-stream image-path)})]
    (json/read-str (:body response) :key-fn keyword)))

(defn valid?
  [path-file]
  (let [body (post-image path-file)]
    (->> body
         :images
         first
         :classifiers
         first
         :classes
         (filter #(= (:class %) type-image))
         (filter #(> (:score %) score))
         empty?
         not)))
