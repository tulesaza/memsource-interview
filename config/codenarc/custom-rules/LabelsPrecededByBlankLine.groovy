package com.memsource.idm.codenarc


import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.stmt.Statement
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

import static org.codenarc.util.AstUtil.findFirstNonAnnotationLine

class LabelsPrecededByBlankLine extends AbstractAstVisitorRule {

    String name = 'com.memsource.idm.codenarc.LabelsPrecededByBlankLine'
    String description = 'Label should be preceded by blank line'
    int priority = 3
    Class astVisitorClass = LabelsPrecededByBlankLineVisitor

}

class LabelsPrecededByBlankLineVisitor extends AbstractAstVisitor {

    @Override
    protected boolean shouldVisitMethod(MethodNode node) {
        return true
    }

    @Override
    protected void visitMethodEx(MethodNode node) {
        def labeledStatements = node.code?.statements?.findAll { it.statementLabel != null }
        if (labeledStatements) {
            labeledStatements.remove(node.firstStatement)
            labeledStatements?.each { Statement stat ->
                def firstLineNo = findFirstNonAnnotationLine(stat, sourceCode)
                def label = sourceCode.getLines().get(firstLineNo - 2).trim()
                def linePrecedingLabelNo = sourceCode.getLines().get(firstLineNo - 3)
                if (!label.empty && !linePrecedingLabelNo.trim().isEmpty()) {
                    addViolation(stat, "Label $label must be preceded by empty line.")
                }
            }
        }
    }

}
