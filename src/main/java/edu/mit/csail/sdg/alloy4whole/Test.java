package edu.mit.csail.sdg.alloy4whole;
import java.util.Arrays;
import java.util.List;
import kodkod.ast.*;
import kodkod.ast.operator.*;
import kodkod.instance.*;
import kodkod.engine.*;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.config.Options;

/* 
  ==================================================
    kodkod formula: 
  ==================================================
    no (this/ThisYear & this/SinceYear) && 
    no (this/Rookie & this/FullGrown) && 
    one (this/MinInterval . (this/MinInterval -> this/MinInterval.val)) && 
    (this/MinInterval . (this/MinInterval -> this/MinInterval.val)) in ints && 
    this/MinInterval.val = Int[4] && 
    one (this/Border . (this/Border -> this/Border.val)) && 
    (this/Border . (this/Border -> this/Border.val)) in ints && 
    this/Border.val = Int[4] && 
    (all this: this/SinceYear | 
      one (this . this/SinceYear.whose) && 
      (this . this/SinceYear.whose) in this/Craftman) && 
    (this/SinceYear.whose . univ) in this/SinceYear && 
    (all this: this/ThisYear + this/SinceYear | 
      one (this . this/Year.val) && 
      (this . this/Year.val) in ints) && 
    (this/Year.val . univ) in (this/ThisYear + this/SinceYear) && 
    (all this: this/Craftman | 
      one (this . this/Craftman.sinceYear) && 
      (this . this/Craftman.sinceYear) in this/SinceYear) && 
    (this/Craftman.sinceYear . univ) in this/Craftman && 
    (all this: this/Rookie + this/FullGrown | 
      (this . this/Career.guys) in this/Craftman) && 
    (this/Career.guys . univ) in (this/Rookie + this/FullGrown) && 
    this/Craftman.sinceYear = ~this/SinceYear.whose && 
    no ((this/Rookie . this/Career.guys) & (this/FullGrown . this/Career.guys)) && 
    (all sYear: this/SinceYear, tYear: this/ThisYear | 
      int[tYear . this/Year.val] >= int[sYear . this/Year.val] && 
      (int[tYear . this/Year.val] - int[sYear . this/Year.val]) >= int[
      this/MinInterval.val]) && 
    (all c: this/Craftman, r: this/Rookie, f: this/FullGrown, y: this/ThisYear, 
     b: this/Border | 
      ((int[y . this/Year.val] - int[(c . this/Craftman.sinceYear) . 
       this/Year.val]) > int[b . (this/Border -> this/Border.val)] => 
       c in (f . this/Career.guys)) && 
      (!((int[y . this/Year.val] - int[(c . this/Craftman.sinceYear) . 
         this/Year.val]) > int[b . (this/Border -> this/Border.val)]) => 
       c in (r . this/Career.guys))) && 
    Int/min = Int/min && 
    Int/zero = Int/zero && 
    Int/max = Int/max && 
    Int/next = Int/next && 
    seq/Int = seq/Int && 
    String = String && 
    this/MinInterval = this/MinInterval && 
    this/Border = this/Border && 
    this/ThisYear = this/ThisYear && 
    this/SinceYear = this/SinceYear && 
    this/Craftman = this/Craftman && 
    this/Rookie = this/Rookie && 
    this/FullGrown = this/FullGrown && 
    this/MinInterval.val = this/MinInterval.val && 
    this/Border.val = this/Border.val && 
    this/Year.val = this/Year.val && 
    this/SinceYear.whose = this/SinceYear.whose && 
    this/Craftman.sinceYear = this/Craftman.sinceYear && 
    this/Career.guys = this/Career.guys
  ==================================================
*/
public final class Test {

public static void main(String[] args) throws Exception {

Relation x0 = Relation.unary("Int/min");
Relation x1 = Relation.unary("Int/zero");
Relation x2 = Relation.unary("Int/max");
Relation x3 = Relation.nary("Int/next", 2);
Relation x4 = Relation.unary("seq/Int");
Relation x5 = Relation.unary("String");
Relation x6 = Relation.unary("this/MinInterval");
Relation x7 = Relation.unary("this/Border");
Relation x8 = Relation.unary("this/ThisYear");
Relation x9 = Relation.unary("this/SinceYear");
Relation x10 = Relation.unary("this/Craftman");
Relation x11 = Relation.unary("this/Rookie");
Relation x12 = Relation.unary("this/FullGrown");
Relation x13 = Relation.unary("this/MinInterval.val");
Relation x14 = Relation.unary("this/Border.val");
Relation x15 = Relation.nary("this/Year.val", 2);
Relation x16 = Relation.nary("this/SinceYear.whose", 2);
Relation x17 = Relation.nary("this/Craftman.sinceYear", 2);
Relation x18 = Relation.nary("this/Career.guys", 2);

List<String> atomlist = Arrays.asList(
 "-1", "-2", "-3", "-4", "-5",
 "-6", "-7", "-8", "0", "1", "2",
 "3", "4", "5", "6", "7", "Border$0",
 "Craftman$0", "Craftman$1", "FullGrown$0", "MinInterval$0", "Rookie$0", "SinceYear$0",
 "SinceYear$1", "ThisYear$0", "unused0", "unused1", "unused2", "unused3",
 "unused4"
);

Universe universe = new Universe(atomlist);
TupleFactory factory = universe.factory();
Bounds bounds = new Bounds(universe);

TupleSet x0_upper = factory.noneOf(1);
x0_upper.add(factory.tuple("-8"));
bounds.boundExactly(x0, x0_upper);

TupleSet x1_upper = factory.noneOf(1);
x1_upper.add(factory.tuple("0"));
bounds.boundExactly(x1, x1_upper);

TupleSet x2_upper = factory.noneOf(1);
x2_upper.add(factory.tuple("7"));
bounds.boundExactly(x2, x2_upper);

TupleSet x3_upper = factory.noneOf(2);
x3_upper.add(factory.tuple("-8").product(factory.tuple("-7")));
x3_upper.add(factory.tuple("-7").product(factory.tuple("-6")));
x3_upper.add(factory.tuple("-6").product(factory.tuple("-5")));
x3_upper.add(factory.tuple("-5").product(factory.tuple("-4")));
x3_upper.add(factory.tuple("-4").product(factory.tuple("-3")));
x3_upper.add(factory.tuple("-3").product(factory.tuple("-2")));
x3_upper.add(factory.tuple("-2").product(factory.tuple("-1")));
x3_upper.add(factory.tuple("-1").product(factory.tuple("0")));
x3_upper.add(factory.tuple("0").product(factory.tuple("1")));
x3_upper.add(factory.tuple("1").product(factory.tuple("2")));
x3_upper.add(factory.tuple("2").product(factory.tuple("3")));
x3_upper.add(factory.tuple("3").product(factory.tuple("4")));
x3_upper.add(factory.tuple("4").product(factory.tuple("5")));
x3_upper.add(factory.tuple("5").product(factory.tuple("6")));
x3_upper.add(factory.tuple("6").product(factory.tuple("7")));
bounds.boundExactly(x3, x3_upper);

TupleSet x4_upper = factory.noneOf(1);
x4_upper.add(factory.tuple("0"));
x4_upper.add(factory.tuple("1"));
x4_upper.add(factory.tuple("2"));
x4_upper.add(factory.tuple("3"));
bounds.boundExactly(x4, x4_upper);

TupleSet x5_upper = factory.noneOf(1);
bounds.boundExactly(x5, x5_upper);

TupleSet x6_upper = factory.noneOf(1);
x6_upper.add(factory.tuple("MinInterval$0"));
bounds.boundExactly(x6, x6_upper);

TupleSet x7_upper = factory.noneOf(1);
x7_upper.add(factory.tuple("Border$0"));
bounds.boundExactly(x7, x7_upper);

TupleSet x8_upper = factory.noneOf(1);
x8_upper.add(factory.tuple("ThisYear$0"));
bounds.boundExactly(x8, x8_upper);

TupleSet x9_upper = factory.noneOf(1);
x9_upper.add(factory.tuple("unused0"));
x9_upper.add(factory.tuple("unused1"));
x9_upper.add(factory.tuple("unused2"));
x9_upper.add(factory.tuple("SinceYear$0"));
x9_upper.add(factory.tuple("SinceYear$1"));
bounds.bound(x9, x9_upper);

TupleSet x10_upper = factory.noneOf(1);
x10_upper.add(factory.tuple("unused3"));
x10_upper.add(factory.tuple("unused4"));
x10_upper.add(factory.tuple("Craftman$0"));
x10_upper.add(factory.tuple("Craftman$1"));
bounds.bound(x10, x10_upper);

TupleSet x11_upper = factory.noneOf(1);
x11_upper.add(factory.tuple("Rookie$0"));
bounds.boundExactly(x11, x11_upper);

TupleSet x12_upper = factory.noneOf(1);
x12_upper.add(factory.tuple("FullGrown$0"));
bounds.boundExactly(x12, x12_upper);

TupleSet x13_upper = factory.noneOf(1);
x13_upper.add(factory.tuple("-8"));
x13_upper.add(factory.tuple("-7"));
x13_upper.add(factory.tuple("-6"));
x13_upper.add(factory.tuple("-5"));
x13_upper.add(factory.tuple("-4"));
x13_upper.add(factory.tuple("-3"));
x13_upper.add(factory.tuple("-2"));
x13_upper.add(factory.tuple("-1"));
x13_upper.add(factory.tuple("0"));
x13_upper.add(factory.tuple("1"));
x13_upper.add(factory.tuple("2"));
x13_upper.add(factory.tuple("3"));
x13_upper.add(factory.tuple("4"));
x13_upper.add(factory.tuple("5"));
x13_upper.add(factory.tuple("6"));
x13_upper.add(factory.tuple("7"));
bounds.bound(x13, x13_upper);

TupleSet x14_upper = factory.noneOf(1);
x14_upper.add(factory.tuple("-8"));
x14_upper.add(factory.tuple("-7"));
x14_upper.add(factory.tuple("-6"));
x14_upper.add(factory.tuple("-5"));
x14_upper.add(factory.tuple("-4"));
x14_upper.add(factory.tuple("-3"));
x14_upper.add(factory.tuple("-2"));
x14_upper.add(factory.tuple("-1"));
x14_upper.add(factory.tuple("0"));
x14_upper.add(factory.tuple("1"));
x14_upper.add(factory.tuple("2"));
x14_upper.add(factory.tuple("3"));
x14_upper.add(factory.tuple("4"));
x14_upper.add(factory.tuple("5"));
x14_upper.add(factory.tuple("6"));
x14_upper.add(factory.tuple("7"));
bounds.bound(x14, x14_upper);

TupleSet x15_upper = factory.noneOf(2);
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-8")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-7")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-6")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-5")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-4")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-3")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-2")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("-1")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("0")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("1")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("2")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("3")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("4")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("5")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("6")));
x15_upper.add(factory.tuple("ThisYear$0").product(factory.tuple("7")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-8")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-7")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-6")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-5")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-4")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-3")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-2")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("-1")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("0")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("1")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("2")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("3")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("4")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("5")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("6")));
x15_upper.add(factory.tuple("unused0").product(factory.tuple("7")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-8")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-7")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-6")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-5")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-4")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-3")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-2")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("-1")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("0")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("1")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("2")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("3")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("4")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("5")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("6")));
x15_upper.add(factory.tuple("unused1").product(factory.tuple("7")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-8")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-7")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-6")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-5")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-4")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-3")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-2")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("-1")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("0")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("1")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("2")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("3")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("4")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("5")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("6")));
x15_upper.add(factory.tuple("unused2").product(factory.tuple("7")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-8")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-7")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-6")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-5")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-4")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-3")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-2")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("-1")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("0")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("1")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("2")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("3")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("4")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("5")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("6")));
x15_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("7")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-8")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-7")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-6")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-5")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-4")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-3")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-2")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("-1")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("0")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("1")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("2")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("3")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("4")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("5")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("6")));
x15_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("7")));
bounds.bound(x15, x15_upper);

TupleSet x16_upper = factory.noneOf(2);
x16_upper.add(factory.tuple("unused0").product(factory.tuple("unused3")));
x16_upper.add(factory.tuple("unused0").product(factory.tuple("unused4")));
x16_upper.add(factory.tuple("unused0").product(factory.tuple("Craftman$0")));
x16_upper.add(factory.tuple("unused0").product(factory.tuple("Craftman$1")));
x16_upper.add(factory.tuple("unused1").product(factory.tuple("unused3")));
x16_upper.add(factory.tuple("unused1").product(factory.tuple("unused4")));
x16_upper.add(factory.tuple("unused1").product(factory.tuple("Craftman$0")));
x16_upper.add(factory.tuple("unused1").product(factory.tuple("Craftman$1")));
x16_upper.add(factory.tuple("unused2").product(factory.tuple("unused3")));
x16_upper.add(factory.tuple("unused2").product(factory.tuple("unused4")));
x16_upper.add(factory.tuple("unused2").product(factory.tuple("Craftman$0")));
x16_upper.add(factory.tuple("unused2").product(factory.tuple("Craftman$1")));
x16_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("unused3")));
x16_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("unused4")));
x16_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("Craftman$0")));
x16_upper.add(factory.tuple("SinceYear$0").product(factory.tuple("Craftman$1")));
x16_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("unused3")));
x16_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("unused4")));
x16_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("Craftman$0")));
x16_upper.add(factory.tuple("SinceYear$1").product(factory.tuple("Craftman$1")));
bounds.bound(x16, x16_upper);

TupleSet x17_upper = factory.noneOf(2);
x17_upper.add(factory.tuple("unused3").product(factory.tuple("unused0")));
x17_upper.add(factory.tuple("unused3").product(factory.tuple("unused1")));
x17_upper.add(factory.tuple("unused3").product(factory.tuple("unused2")));
x17_upper.add(factory.tuple("unused3").product(factory.tuple("SinceYear$0")));
x17_upper.add(factory.tuple("unused3").product(factory.tuple("SinceYear$1")));
x17_upper.add(factory.tuple("unused4").product(factory.tuple("unused0")));
x17_upper.add(factory.tuple("unused4").product(factory.tuple("unused1")));
x17_upper.add(factory.tuple("unused4").product(factory.tuple("unused2")));
x17_upper.add(factory.tuple("unused4").product(factory.tuple("SinceYear$0")));
x17_upper.add(factory.tuple("unused4").product(factory.tuple("SinceYear$1")));
x17_upper.add(factory.tuple("Craftman$0").product(factory.tuple("unused0")));
x17_upper.add(factory.tuple("Craftman$0").product(factory.tuple("unused1")));
x17_upper.add(factory.tuple("Craftman$0").product(factory.tuple("unused2")));
x17_upper.add(factory.tuple("Craftman$0").product(factory.tuple("SinceYear$0")));
x17_upper.add(factory.tuple("Craftman$0").product(factory.tuple("SinceYear$1")));
x17_upper.add(factory.tuple("Craftman$1").product(factory.tuple("unused0")));
x17_upper.add(factory.tuple("Craftman$1").product(factory.tuple("unused1")));
x17_upper.add(factory.tuple("Craftman$1").product(factory.tuple("unused2")));
x17_upper.add(factory.tuple("Craftman$1").product(factory.tuple("SinceYear$0")));
x17_upper.add(factory.tuple("Craftman$1").product(factory.tuple("SinceYear$1")));
bounds.bound(x17, x17_upper);

TupleSet x18_upper = factory.noneOf(2);
x18_upper.add(factory.tuple("Rookie$0").product(factory.tuple("unused3")));
x18_upper.add(factory.tuple("Rookie$0").product(factory.tuple("unused4")));
x18_upper.add(factory.tuple("Rookie$0").product(factory.tuple("Craftman$0")));
x18_upper.add(factory.tuple("Rookie$0").product(factory.tuple("Craftman$1")));
x18_upper.add(factory.tuple("FullGrown$0").product(factory.tuple("unused3")));
x18_upper.add(factory.tuple("FullGrown$0").product(factory.tuple("unused4")));
x18_upper.add(factory.tuple("FullGrown$0").product(factory.tuple("Craftman$0")));
x18_upper.add(factory.tuple("FullGrown$0").product(factory.tuple("Craftman$1")));
bounds.bound(x18, x18_upper);

bounds.boundExactly(-8,factory.range(factory.tuple("-8"),factory.tuple("-8")));
bounds.boundExactly(-7,factory.range(factory.tuple("-7"),factory.tuple("-7")));
bounds.boundExactly(-6,factory.range(factory.tuple("-6"),factory.tuple("-6")));
bounds.boundExactly(-5,factory.range(factory.tuple("-5"),factory.tuple("-5")));
bounds.boundExactly(-4,factory.range(factory.tuple("-4"),factory.tuple("-4")));
bounds.boundExactly(-3,factory.range(factory.tuple("-3"),factory.tuple("-3")));
bounds.boundExactly(-2,factory.range(factory.tuple("-2"),factory.tuple("-2")));
bounds.boundExactly(-1,factory.range(factory.tuple("-1"),factory.tuple("-1")));
bounds.boundExactly(0,factory.range(factory.tuple("0"),factory.tuple("0")));
bounds.boundExactly(1,factory.range(factory.tuple("1"),factory.tuple("1")));
bounds.boundExactly(2,factory.range(factory.tuple("2"),factory.tuple("2")));
bounds.boundExactly(3,factory.range(factory.tuple("3"),factory.tuple("3")));
bounds.boundExactly(4,factory.range(factory.tuple("4"),factory.tuple("4")));
bounds.boundExactly(5,factory.range(factory.tuple("5"),factory.tuple("5")));
bounds.boundExactly(6,factory.range(factory.tuple("6"),factory.tuple("6")));
bounds.boundExactly(7,factory.range(factory.tuple("7"),factory.tuple("7")));

Expression x21=x8.intersection(x9);
Formula x20=x21.no();
Expression x23=x11.intersection(x12);
Formula x22=x23.no();
Expression x27=x6.product(x13);
Expression x26=x6.join(x27);
Formula x25=x26.one();
Formula x28=x26.in(Expression.INTS);
Formula x24=x25.and(x28);
IntExpression x32=IntConstant.constant(4);
Expression x31=x32.toExpression();
Formula x30=x13.eq(x31);
Expression x36=x7.product(x14);
Expression x35=x7.join(x36);
Formula x34=x35.one();
Formula x37=x35.in(Expression.INTS);
Formula x33=x34.and(x37);
IntExpression x40=IntConstant.constant(4);
Expression x39=x40.toExpression();
Formula x38=x14.eq(x39);
Variable x43=Variable.unary("this");
Decls x42=x43.oneOf(x9);
Expression x46=x43.join(x16);
Formula x45=x46.one();
Formula x47=x46.in(x10);
Formula x44=x45.and(x47);
Formula x41=x44.forAll(x42);
Expression x49=x16.join(Expression.UNIV);
Formula x48=x49.in(x9);
Variable x53=Variable.unary("this");
Expression x54=x8.union(x9);
Decls x52=x53.oneOf(x54);
Expression x57=x53.join(x15);
Formula x56=x57.one();
Formula x58=x57.in(Expression.INTS);
Formula x55=x56.and(x58);
Formula x51=x55.forAll(x52);
Expression x60=x15.join(Expression.UNIV);
Formula x59=x60.in(x54);
Variable x63=Variable.unary("this");
Decls x62=x63.oneOf(x10);
Expression x66=x63.join(x17);
Formula x65=x66.one();
Formula x67=x66.in(x9);
Formula x64=x65.and(x67);
Formula x61=x64.forAll(x62);
Expression x69=x17.join(Expression.UNIV);
Formula x68=x69.in(x10);
Variable x72=Variable.unary("this");
Expression x73=x11.union(x12);
Decls x71=x72.oneOf(x73);
Expression x75=x72.join(x18);
Formula x74=x75.in(x10);
Formula x70=x74.forAll(x71);
Expression x77=x18.join(Expression.UNIV);
Formula x76=x77.in(x73);
Expression x79=x16.transpose();
Formula x78=x17.eq(x79);
Expression x82=x11.join(x18);
Expression x83=x12.join(x18);
Expression x81=x82.intersection(x83);
Formula x80=x81.no();
Variable x87=Variable.unary("sYear");
Decls x86=x87.oneOf(x9);
Variable x89=Variable.unary("tYear");
Decls x88=x89.oneOf(x8);
Decls x85=x86.and(x88);
Expression x93=x89.join(x15);
IntExpression x92=x93.sum();
Expression x95=x87.join(x15);
IntExpression x94=x95.sum();
Formula x91=x92.gte(x94);
Expression x99=x89.join(x15);
IntExpression x98=x99.sum();
Expression x101=x87.join(x15);
IntExpression x100=x101.sum();
IntExpression x97=x98.minus(x100);
IntExpression x102=x13.sum();
Formula x96=x97.gte(x102);
Formula x90=x91.and(x96);
Formula x84=x90.forAll(x85);
Variable x106=Variable.unary("c");
Decls x105=x106.oneOf(x10);
Variable x108=Variable.unary("r");
Decls x107=x108.oneOf(x11);
Variable x110=Variable.unary("f");
Decls x109=x110.oneOf(x12);
Variable x112=Variable.unary("y");
Decls x111=x112.oneOf(x8);
Variable x114=Variable.unary("b");
Decls x113=x114.oneOf(x7);
Decls x104=x105.and(x107).and(x109).and(x111).and(x113);
Expression x120=x112.join(x15);
IntExpression x119=x120.sum();
Expression x123=x106.join(x17);
Expression x122=x123.join(x15);
IntExpression x121=x122.sum();
IntExpression x118=x119.minus(x121);
Expression x125=x114.join(x36);
IntExpression x124=x125.sum();
Formula x117=x118.gt(x124);
Expression x127=x110.join(x18);
Formula x126=x106.in(x127);
Formula x116=x117.implies(x126);
Formula x129=x117.not();
Expression x131=x108.join(x18);
Formula x130=x106.in(x131);
Formula x128=x129.implies(x130);
Formula x115=x116.and(x128);
Formula x103=x115.forAll(x104);
Formula x132=x0.eq(x0);
Formula x133=x1.eq(x1);
Formula x134=x2.eq(x2);
Formula x135=x3.eq(x3);
Formula x136=x4.eq(x4);
Formula x137=x5.eq(x5);
Formula x138=x6.eq(x6);
Formula x139=x7.eq(x7);
Formula x140=x8.eq(x8);
Formula x141=x9.eq(x9);
Formula x142=x10.eq(x10);
Formula x143=x11.eq(x11);
Formula x144=x12.eq(x12);
Formula x145=x13.eq(x13);
Formula x146=x14.eq(x14);
Formula x147=x15.eq(x15);
Formula x148=x16.eq(x16);
Formula x149=x17.eq(x17);
Formula x150=x18.eq(x18);
Formula x19=Formula.compose(FormulaOperator.AND, x20, x22, x24, x30, x33, x38, x41, x48, x51, x59, x61, x68, x70, x76, x78, x80, x84, x103, x132, x133, x134, x135, x136, x137, x138, x139, x140, x141, x142, x143, x144, x145, x146, x147, x148, x149, x150);

Solver solver = new Solver();
solver.options().setSolver(SATFactory.DefaultSAT4J);
solver.options().setBitwidth(4);
solver.options().setFlatten(false);
solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
solver.options().setSymmetryBreaking(20);
solver.options().setSkolemDepth(0);
System.out.println("Solving...");
System.out.flush();
Solution sol = solver.solve(x19,bounds);
System.out.println(sol.toString());
}}
