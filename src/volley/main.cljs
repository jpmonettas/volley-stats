(ns volley.main
  (:require [reagent.dom :as rdom]
            [re-frame.core :as re-frame]
            [volley.views :as views]
            [volley.subs]
            [volley.events]))

(defn ^:dev/after-load mount-component []
  (println "Mounting volley.views/main over #app")
  (rdom/render [views/main]
               (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:volley/initialize])
  (mount-component))
