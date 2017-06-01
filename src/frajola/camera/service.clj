(ns frajola.camera.service
  (:require [frajola.config :as config])
  (:import [org.gphoto2.CameraFile]
           [org.gphoto2.CameraUtils]
           [org.gphoto2.Camera]))

(def SUFFIX "_frajola.jpg")

(defn- build-path
  []
  (let [base-path config/picture-path]
    (str base-path (System/currentTimeMillis) SUFFIX)))

(defn- save-file
  [path cam-file]
  (let [file (clojure.java.io/file path)]
    (println path)
    (.save cam-file path)
    (org.gphoto2.CameraUtils/closeQuietly cam-file)))

(defn take-picture
  []
  (let [path (build-path) cam (org.gphoto2.Camera.)]
    (.initialize cam)
    (save-file path (.captureImage cam))
    (org.gphoto2.CameraUtils/closeQuietly cam)
    path))
