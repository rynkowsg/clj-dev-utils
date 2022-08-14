(ns clj-dev-utils.deps
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.tools.deps.alpha.repl :as deps-repl]))

(defn- read-edn [path]
  (-> path (io/file) (slurp) (edn/read-string)))

(defn refresh-deps-libs
  "refresh libraries defined in deps.edn"
  ([] (refresh-deps-libs "deps.edn" [[:deps] [:aliases :dev :extra-deps]]))
  ([filepath key-paths]
   (let [deps-edn    (read-edn filepath)
         deps (->> key-paths
                   (map #(get-in deps-edn %))
                   (into {}))]
     (deps-repl/add-libs deps)
     deps)))

(comment
 (refresh-deps-libs)
 (refresh-deps-libs "deps.edn" [[:deps]])
 (refresh-deps-libs "deps.edn" [[:deps] [:aliases :dev :extra-deps]])

 (deps-repl/libs)
 (deps-repl/current-basis))
