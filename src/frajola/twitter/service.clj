(ns frajola.twitter.service
  (:use [twitter.oauth]
        [twitter.callbacks]
        [twitter.callbacks.handlers]
        [frajola.config :as config]
        [twitter.api.restful])
  (:require [twitter.request :refer [file-body-part
                                     prepare-request-with-multi
                                     status-body-part]]
            [frajola.recognition.service :as recognition.service]))

(def credentials (make-oauth-creds (config/env "TWITTER_CONSUMER_KEY")
                                   (config/env "TWITTER_CONSUMER_SECRET")
                                   (config/env "TWITTER_TOKEN")
                                   (config/env "TWITTER_TOKEN_SECRET")))

(defn update-status [image-path]
  (if (recognition.service/valid? image-path)
      (statuses-update-with-media :oauth-creds credentials
                                  :body [(file-body-part image-path)])))
