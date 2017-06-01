(ns frajola.handler
  (:use [frajola.service :as service])
  (:require
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(defroutes app-routes
  (GET "/" [] { :body {:message "Bem vindo ao frajola, consulte o html dentro do projeto em: resources/public/index.html"}})

  (POST "/take-picture" { params :params } (service/take-picture))

  (GET "/pictures" [] (service/list-pictures))

  (route/not-found "Frajola Not Found"))

(def app
  (-> app-routes
      wrap-json-response
      wrap-json-body))
