# XML Generator
## Purpose
This software is designed to simplify the generation and positioning of templates in Inductive's [Ignition](https://inductiveautomation.com/). The process of creating hundreds (or even thousands) of labels can be a time consuming and very tedious process. We wanted to come up with a way where we could create all these labels just by entering their respective names and positions.
## Description
The program takes valid user input and produces Ignition standard XML that can be directly copied into a template or window. It accomplishes this by building XML content for each user-provided label name. If repositioning is selected, the program modifies the `<r2dd>` tag for each respective label in order to move it to the desired coordinates. Once each label's code has been built, the program optimizes the XML by inserting `<ref>` tags and ID's into duplicated lines of code, which significantly reduces the final output. This is particularly useful in situations where thousands of labels are being generated at a single time. 
## Usage
1. Download the Application folder off Github repository.
2. [Import](https://docs.inductiveautomation.com/display/DOC80/Project+Export+and+Import) LabelExport.zip into the template's subdirectory of the Ignition Vision application.
3. Launch XMLGenerator.exe and follow instructions on screen to generate XML.
4. Copy generated XML and paste into Ignition window.

## Useful REGEX
These regular expressions can be helpful when pulling label data directly out of ignition XML. They are typically used with the find-replace functionality in notepad++ or other text editors. 

Slims down input by removing lines that do not feature the `<r2dd>` positioning tag and templates that feature X,Y, or Z in the name:
`^((?!r2dd|X|Y|Z).)*$`

Removes headers and footers and tabs of leftover tags:
`<r2dd>|</r2dd>|<str>|</str>|\t`
