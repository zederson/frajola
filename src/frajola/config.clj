(ns frajola.config)

(defn env
  [attribute]
  (System/getenv attribute))
