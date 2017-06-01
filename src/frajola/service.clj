(ns frajola.service
  (:require [frajola.twitter.service :as tweet]
            [frajola.config :as config]
            [frajola.camera.service :as dslr]
            [clojure.java.io :as io]))

(defn- list-files
  []
  (let [path config/picture-path]
    (filter
      (fn [file-name] (re-find (re-pattern dslr/SUFFIX) file-name))
      (seq (.list (clojure.java.io/file path))))))

(defn- build-data-pictures
  []
  (map
    (fn [file-name]
      { :name (str config/picture-path file-name) })
    (list-files)))

(defn delete
  [path]
  (if (.exists (io/as-file path))
    (io/delete-file path)))

(defn take-picture
  []
  (let [file (dslr/take-picture)]
    (future
      (tweet/update-status file)
      (delete file)))
  {:status 201 :headers {"Content-Type" "application/json"} :body { :status :ok }})

(defn list-pictures
  []
  { :status 200 :headers {"Content-Type" "application/json"} :body (build-data-pictures) })
