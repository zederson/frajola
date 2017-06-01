(ns frajola.config)

(defn env
  [attribute]
  (System/getenv attribute))

(def picture-path
  (or (env "PICTURE_PATH") "/tmp/"))
