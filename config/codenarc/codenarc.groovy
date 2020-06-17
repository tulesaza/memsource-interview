ruleset {
    description '''
        RuleSet for checking groovy code. Include here all rulesets comming with codenarc and then selectively disable 
        or configure rules.

        The latest version: http://codenarc.sourceforge.net/StarterRuleSet-AllRulesByCategory.groovy.txt
        '''

    ruleset('rulesets/basic.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/comments.xml') {
        ClassJavadoc { enabled = false }
    }
    ruleset('rulesets/concurrency.xml')
    ruleset('rulesets/convention.xml') {
        CompileStatic { enabled = false }
        MethodReturnTypeRequired { enabled = false }
        NoDef { enabled = false }
        VariableTypeRequired { enabled = false }
        StaticMethodsBeforeInstanceMethods { enabled = false }
    }
    ruleset('rulesets/design.xml') {
        AbstractClassWithoutAbstractMethod { enabled = false }
        Instanceof { enabled = false }
    }
    ruleset('rulesets/dry.xml') {
        DuplicateListLiteral { enabled = false }
        DuplicateMapLiteral { enabled = false }
        DuplicateNumberLiteral { enabled = false }
        DuplicateStringLiteral { enabled = false }
    }
    ruleset('rulesets/enhanced.xml')
    ruleset('rulesets/exceptions.xml') {
        ThrowRuntimeException { enabled = false }
    }
    ruleset('rulesets/formatting.xml') {
        SpaceAroundMapEntryColon {
            characterAfterColonRegex = /\s/
            characterBeforeColonRegex = /./
        }
        BracesForIfElse { validateElse = true }
        Indentation { enabled = false }
        LineLength {
            length = 120
            ignoreImportStatements = true
            ignorePackageStatements = true
        }
    }
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/grails.xml')
    ruleset('rulesets/groovyism.xml')
    ruleset('rulesets/imports.xml') {
        MisorderedStaticImports { comesBefore = false }
        NoWildcardImports { ignoreStaticImports = true }
    }
    ruleset('rulesets/jdbc.xml')
    ruleset('rulesets/junit.xml') {
        JUnitPublicNonTestMethod { enabled = false }
        JUnitPublicField { enabled = false }
        JUnitPublicNonTestMethod { enabled = false }
        JUnitPublicProperty { enabled = false }
    }
    ruleset('rulesets/logging.xml')
    ruleset('rulesets/naming.xml') {
        MethodName { regex = '[a-z]\\w*|.+ .+' }
        FactoryMethodName { regex = 'build.*' }
        VariableName { finalRegex = null }
    }
    ruleset('rulesets/security.xml')
    ruleset('rulesets/serialization.xml')
    ruleset('rulesets/size.xml') {
        AbcMetric { enabled = false }
        ClassSize { enabled = false }
        CrapMetric { enabled = false }
        CyclomaticComplexity { enabled = false }
        MethodCount { enabled = false }
        MethodSize { enabled = false }
    }
    ruleset('rulesets/unnecessary.xml') {
        UnnecessaryObjectReferences { enabled = false }
        UnnecessaryReturnKeyword { enabled = false }
    }
    ruleset('rulesets/unused.xml') {
        UnusedMethodParameter { enabled = false }
    }

    //Custom Rules
    rule('file:config/codenarc/custom-rules/LabelsPrecededByBlankLine.groovy')
}
