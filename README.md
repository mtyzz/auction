# tally-auction

## Overview

Tally-auction is a Clojure command-line application that takes a list of bids and the item on which they are bidding, and uses the given maximum bids and bid increments to determine the winning bid and bidder.

## How to use
This program reads lines of the format "Name start-bid max-bid increment, Name start-bid max-bid increment, item-for-auction" from standard input until the end of file, then prints the result of the auction to standard out. Users with [Leiningen](https://github.com/technomancy/leiningen) installed can clone the repo, navigate to the root directory, and run the following command to start an auction:

```lein run <<EOF
   ;;as many of these as there are bids
   ;;Name should be the bidders name. The rest should be integers
   Name start-bid max-bid increment,
   ;;After all bids have been input, end with the item name
   item-name, 
   EOF
```
Users without Leiningen installed can run it from an uberjar using the same syntax, e.g.
$ java -jar tally-auction-0.1.0-SNAPSHOT-standalone.jar
Name start-bid max-bid increment,
Name start-bid max-bid increment,
Name start-bid max-bid increment,
item

## Running unit tests
From the root directory, call `lein run test`.

## Upcoming work
Clojure is not a great way to write command-line tools. A different interface, either by an HTTP api or a clojurescript app that can store input state, would be easier to use and allow more input flexibility.

I was getting some dependency errors when I tried to compile and use clojure.spec that stack overflow tend to come from some outdated deps on my machine. Had I time to sort that out, I would have liked to use spec to validate input.
 
