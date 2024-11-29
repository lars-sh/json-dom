# Changelog
All notable changes to this project will be documented in this file.

<a name="0.9.5"></a>

## [0.9.5](https://github.com/lars-sh/json-dom/compare/4484734a77624ebcc91048d749c5bc1801713f96...4b5b7a532ef19b4b8ca4fd17bb35ab6b7b6fd156) (2024-11-29)

Download at [Maven Search](https://search.maven.org/artifact/de.lars-sh/json-dom/0.9.5/jar)

* Update Parent POM to fix compatibility with the latest `de.larssh.utils.Finals.lazy`

<a name="0.9.4"></a>

## [0.9.4](https://github.com/lars-sh/json-dom/compare/da2c82bc7aa6aab4782bdbec935f0563d512a828...4484734a77624ebcc91048d749c5bc1801713f96) (2024-06-11)

Download at [Maven Search](https://search.maven.org/artifact/de.lars-sh/json-dom/0.9.4/jar)

### Changed
* To simplify accessing elements inside JSON arrays their unified name is now "element" instead of "n0", "n1", "n2", ...

### Fixed
* Make the Jakarta JSON Processing dependency optional

<a name="0.9.3"></a>

## [0.9.3](https://github.com/lars-sh/json-dom/compare/b967cbc1c1f87ce79033242ab027ead1fdb9b64c...da2c82bc7aa6aab4782bdbec935f0563d512a828) (2024-03-20)

Download at [Maven Search](https://search.maven.org/artifact/de.lars-sh/json-dom/0.9.3/jar)

### Fixed
* StackOverflowError

<a name="0.9.2"></a>

## [0.9.2](https://github.com/lars-sh/json-dom/compare/88a2a0fbea8b21b5ff0d3973a49d8494e089dd3a...b967cbc1c1f87ce79033242ab027ead1fdb9b64c) (2024-03-20)

Download at [Maven Search](https://search.maven.org/artifact/de.lars-sh/json-dom/0.9.2/jar)

### Added
* Jakarta JSON Processing implementation of JsonDomValue

### Changed
* The DOM structure to be more intuitive
* Constructor parameter of `de.larssh.json.dom.JsonDomNamedNodeMap` from `Map` to `List`

<a name="0.9.1"></a>

## [0.9.1](https://github.com/lars-sh/json-dom/compare/56d9c1bf3e529a15c9a082ce18c76f608fd1a2db...88a2a0fbea8b21b5ff0d3973a49d8494e089dd3a) (2019-05-06)

Download at [Maven Search](https://search.maven.org/artifact/de.lars-sh/json-dom/0.9.1/jar)

### Added
* JSON Processing (aka JSR-374) implementation of JsonDomValue

### Changed
* Moved JsonDomValue into its own package
* Updated signature of JsonDomValue.getChildren()

<a name="0.9.0"></a>

## [0.9.0](https://github.com/lars-sh/json-dom/commit/56d9c1bf3e529a15c9a082ce18c76f608fd1a2db) (2018-12-13)

Download at [Maven Search](https://search.maven.org/artifact/de.lars-sh/json-dom/0.9.0/jar)

* Initial release
