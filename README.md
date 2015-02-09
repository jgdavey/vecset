# vecset

Vecset: a vector with set-like lookup.

## Dependency information

Leiningen dependency information:

```clojure
[com.joshuadavey/vecset "0.1.0"]
```

## Usage

```clojure
(ns com.example.your-application
  (:require [com.joshuadavey.vecset :as vecset]))
```

To create an empty Vecset, use `vecset` with no arguments:

```clojure
(def empty-vecset (vecset))
```

You can also create a Vecset from an existing collection:

```clojure
(def my-vecset (vecset [1 2 3 4 4 5 5 5]))

(contains? my-vecset 2)
;=> true

(contains? my-vecset 0)
;=> false

(nth my-vecset 0)
;=> 1

(seq my-vecset)
;=> (1 2 3 4 4 5 5 5)

(contains? (conj my-vecset 6) 6)
;=> true
```

## License

Copyright © 2015 Joshua Davey

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
