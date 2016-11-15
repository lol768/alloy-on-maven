package tmp;

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
    (all deleteUser_this: this/User | 
      (deleteUser_this . this/User.photos) in this/Photo) && 
    (this/User.photos . univ) in this/User && 
    (all deleteUser_this: this/Photo | 
      one (deleteUser_this . this/Photo.owner) && 
      (deleteUser_this . this/Photo.owner) in this/User) && 
    (this/Photo.owner . univ) in this/Photo && 
    (this/PhotoRel_RightSt . (this/PhotoRel_RightSt -> this/PhotoRel_RightSt.u)) in 
    this/User && 
    some (this/PhotoRel_RightSt . (this/PhotoRel_RightSt -> 
    this/PhotoRel_RightSt.p)) && 
    (this/PhotoRel_RightSt . (this/PhotoRel_RightSt -> this/PhotoRel_RightSt.p)) in 
    this/Photo && 
    (this/PhotoRel_RightSt . (this/PhotoRel_RightSt -> this/PhotoRel_RightSt.rel
    )) in ((this/PhotoRel_RightSt . (this/PhotoRel_RightSt -> 
    this/PhotoRel_RightSt.p)) -> (this/PhotoRel_RightSt . (this/PhotoRel_RightSt -> 
    this/PhotoRel_RightSt.u))) && 
    this/PhotoRel_RightSt.rel = ((this/PhotoRel_RightSt.p -> univ) & 
    this/Photo.owner) && 
    (this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
    this/PhotoRel_ChildRemovedSt.delUser)) in this/User && 
    (this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
    this/PhotoRel_ChildRemovedSt.u'')) in this/User && 
    some (this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
    this/PhotoRel_ChildRemovedSt.p'')) && 
    (this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
    this/PhotoRel_ChildRemovedSt.p'')) in this/Photo && 
    (this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
    this/PhotoRel_ChildRemovedSt.rel'')) in ((this/PhotoRel_ChildRemovedSt . (
    this/PhotoRel_ChildRemovedSt -> this/PhotoRel_ChildRemovedSt.p'')) -> ((
    this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
    this/PhotoRel_ChildRemovedSt.u'')) - (this/PhotoRel_ChildRemovedSt . (
    this/PhotoRel_ChildRemovedSt -> this/PhotoRel_ChildRemovedSt.delUser)))) && 
    (all v28: this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
     this/PhotoRel_ChildRemovedSt.p'') | 
      (v28 . (this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
      this/PhotoRel_ChildRemovedSt.rel''))) in ((this/PhotoRel_ChildRemovedSt . 
      (this/PhotoRel_ChildRemovedSt -> this/PhotoRel_ChildRemovedSt.u'')) - (
      this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
      this/PhotoRel_ChildRemovedSt.delUser)))) && 
    (all v29: (this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
     this/PhotoRel_ChildRemovedSt.u'')) - (this/PhotoRel_ChildRemovedSt . (
     this/PhotoRel_ChildRemovedSt -> this/PhotoRel_ChildRemovedSt.delUser)) | 
      some ((this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
      this/PhotoRel_ChildRemovedSt.rel'')) . v29) && 
      ((this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
      this/PhotoRel_ChildRemovedSt.rel'')) . v29) in (
      this/PhotoRel_ChildRemovedSt . (this/PhotoRel_ChildRemovedSt -> 
      this/PhotoRel_ChildRemovedSt.p''))) && 
    this/PhotoRel_ChildRemovedSt.rel'' = ((this/PhotoRel_ChildRemovedSt.p'' -> 
    univ) & this/Photo.owner) && 
    this/PhotoRel_ChildRemovedSt.p'' = ((this/PhotoRel_ChildRemovedSt.u'' - 
    this/PhotoRel_ChildRemovedSt.delUser) . this/User.photos) && 
    some (this/PhotoRel_AllRemovedSt . (this/PhotoRel_AllRemovedSt -> 
    this/PhotoRel_AllRemovedSt.u')) && 
    (this/PhotoRel_AllRemovedSt . (this/PhotoRel_AllRemovedSt -> 
    this/PhotoRel_AllRemovedSt.u')) in this/User && 
    some (this/PhotoRel_AllRemovedSt . (this/PhotoRel_AllRemovedSt -> 
    this/PhotoRel_AllRemovedSt.p')) && 
    (this/PhotoRel_AllRemovedSt . (this/PhotoRel_AllRemovedSt -> 
    this/PhotoRel_AllRemovedSt.p')) in this/Photo && 
    (this/PhotoRel_AllRemovedSt . (this/PhotoRel_AllRemovedSt -> 
    this/PhotoRel_AllRemovedSt.rel')) in ((this/PhotoRel_AllRemovedSt . (
    this/PhotoRel_AllRemovedSt -> this/PhotoRel_AllRemovedSt.p')) -> (
    this/PhotoRel_AllRemovedSt . (this/PhotoRel_AllRemovedSt -> 
    this/PhotoRel_AllRemovedSt.u'))) && 
    this/PhotoRel_AllRemovedSt.rel' = ((this/PhotoRel_AllRemovedSt.p' -> univ) & 
    this/Photo.owner) && 
    this/PhotoRel_AllRemovedSt.p' = (this/PhotoRel_AllRemovedSt.u' . 
    this/User.photos) && 
    this/User.photos = ~this/Photo.owner && 
    (all deleteUser_everyUser: this/User, deleteUser_everyPhoto: this/Photo, 
     deleteUser_s: this/PhotoRel_RightSt | 
      deleteUser_everyUser in (deleteUser_s . (this/PhotoRel_RightSt -> 
      this/PhotoRel_RightSt.u)) && 
      deleteUser_everyPhoto in (deleteUser_s . (this/PhotoRel_RightSt -> 
      this/PhotoRel_RightSt.p))) && 
    (all deleteUser_before: this/PhotoRel_RightSt, deleteUser_process: 
     this/PhotoRel_ChildRemovedSt, deleteUser_after: this/PhotoRel_AllRemovedSt | 
      (deleteUser_process . (this/PhotoRel_ChildRemovedSt -> 
      this/PhotoRel_ChildRemovedSt.delUser)) = ((deleteUser_before . (
      this/PhotoRel_RightSt -> this/PhotoRel_RightSt.u)) - (deleteUser_after . (
      this/PhotoRel_AllRemovedSt -> this/PhotoRel_AllRemovedSt.u'))) && 
      (deleteUser_process . (this/PhotoRel_ChildRemovedSt -> 
      this/PhotoRel_ChildRemovedSt.u'')) = (deleteUser_before . (
      this/PhotoRel_RightSt -> this/PhotoRel_RightSt.u))) && 
    !(all deleteUser_user: this/User, deleteUser_rightSt: this/PhotoRel_RightSt, 
      deleteUser_childRemoveSt: this/PhotoRel_ChildRemovedSt, 
      deleteUser_allRemovedSt: this/PhotoRel_AllRemovedSt | 
       !(deleteUser_user = ((deleteUser_rightSt . (this/PhotoRel_RightSt -> 
         this/PhotoRel_RightSt.u)) - (deleteUser_allRemovedSt . (
         this/PhotoRel_AllRemovedSt -> this/PhotoRel_AllRemovedSt.u')))) || 
       (((deleteUser_rightSt . (this/PhotoRel_RightSt -> 
        this/PhotoRel_RightSt.rel)) - (deleteUser_childRemoveSt . (
        this/PhotoRel_ChildRemovedSt -> this/PhotoRel_ChildRemovedSt.rel''))) = 
        ((((deleteUser_childRemoveSt . (this/PhotoRel_ChildRemovedSt -> 
        this/PhotoRel_ChildRemovedSt.delUser)) . this/User.photos) -> univ) & 
        this/Photo.owner) && 
        ((deleteUser_rightSt . (this/PhotoRel_RightSt -> 
        this/PhotoRel_RightSt.rel)) - (deleteUser_allRemovedSt . (
        this/PhotoRel_AllRemovedSt -> this/PhotoRel_AllRemovedSt.rel'))) = (((
        deleteUser_user . this/User.photos) -> univ) & this/Photo.owner))) && 
    Int/next = Int/next && 
    seq/Int = seq/Int && 
    String = String && 
    this/User = this/User && 
    this/Photo = this/Photo && 
    this/PhotoRel_RightSt = this/PhotoRel_RightSt && 
    this/PhotoRel_ChildRemovedSt = this/PhotoRel_ChildRemovedSt && 
    this/PhotoRel_AllRemovedSt = this/PhotoRel_AllRemovedSt && 
    this/User.photos = this/User.photos && 
    this/Photo.owner = this/Photo.owner && 
    this/PhotoRel_RightSt.u = this/PhotoRel_RightSt.u && 
    this/PhotoRel_RightSt.p = this/PhotoRel_RightSt.p && 
    this/PhotoRel_RightSt.rel = this/PhotoRel_RightSt.rel && 
    this/PhotoRel_ChildRemovedSt.delUser = this/PhotoRel_ChildRemovedSt.delUser && 
    this/PhotoRel_ChildRemovedSt.u'' = this/PhotoRel_ChildRemovedSt.u'' && 
    this/PhotoRel_ChildRemovedSt.p'' = this/PhotoRel_ChildRemovedSt.p'' && 
    this/PhotoRel_ChildRemovedSt.rel'' = this/PhotoRel_ChildRemovedSt.rel'' && 
    this/PhotoRel_AllRemovedSt.u' = this/PhotoRel_AllRemovedSt.u' && 
    this/PhotoRel_AllRemovedSt.p' = this/PhotoRel_AllRemovedSt.p' && 
    this/PhotoRel_AllRemovedSt.rel' = this/PhotoRel_AllRemovedSt.rel'
  ==================================================
*/
public final class MyTest {

public static void main(String[] args) throws Exception {

Relation x0 = Relation.nary("Int/next", 2);
Relation x1 = Relation.unary("seq/Int");
Relation x2 = Relation.unary("String");
Relation x3 = Relation.unary("this/User");
Relation x4 = Relation.unary("this/Photo");
Relation x5 = Relation.unary("this/PhotoRel_RightSt");
Relation x6 = Relation.unary("this/PhotoRel_ChildRemovedSt");
Relation x7 = Relation.unary("this/PhotoRel_AllRemovedSt");
Relation x8 = Relation.nary("this/User.photos", 2);
Relation x9 = Relation.nary("this/Photo.owner", 2);
Relation x10 = Relation.unary("this/PhotoRel_RightSt.u");
Relation x11 = Relation.unary("this/PhotoRel_RightSt.p");
Relation x12 = Relation.nary("this/PhotoRel_RightSt.rel", 2);
Relation x13 = Relation.unary("this/PhotoRel_ChildRemovedSt.delUser");
Relation x14 = Relation.unary("this/PhotoRel_ChildRemovedSt.u''");
Relation x15 = Relation.unary("this/PhotoRel_ChildRemovedSt.p''");
Relation x16 = Relation.nary("this/PhotoRel_ChildRemovedSt.rel''", 2);
Relation x17 = Relation.unary("this/PhotoRel_AllRemovedSt.u'");
Relation x18 = Relation.unary("this/PhotoRel_AllRemovedSt.p'");
Relation x19 = Relation.nary("this/PhotoRel_AllRemovedSt.rel'", 2);

List<String> atomlist = Arrays.asList(
 "Photo$0", "Photo$1", "Photo$2", "PhotoRel_AllRemovedSt$0", "PhotoRel_ChildRemovedSt$0",
 "PhotoRel_RightSt$0", "User$0", "User$1", "User$2"
);

Universe universe = new Universe(atomlist);
TupleFactory factory = universe.factory();
Bounds bounds = new Bounds(universe);

TupleSet x0_upper = factory.noneOf(2);
bounds.boundExactly(x0, x0_upper);

TupleSet x1_upper = factory.noneOf(1);
bounds.boundExactly(x1, x1_upper);

TupleSet x2_upper = factory.noneOf(1);
bounds.boundExactly(x2, x2_upper);

TupleSet x3_upper = factory.noneOf(1);
x3_upper.add(factory.tuple("User$0"));
x3_upper.add(factory.tuple("User$1"));
x3_upper.add(factory.tuple("User$2"));
bounds.bound(x3, x3_upper);

TupleSet x4_upper = factory.noneOf(1);
x4_upper.add(factory.tuple("Photo$0"));
x4_upper.add(factory.tuple("Photo$1"));
x4_upper.add(factory.tuple("Photo$2"));
bounds.bound(x4, x4_upper);

TupleSet x5_upper = factory.noneOf(1);
x5_upper.add(factory.tuple("PhotoRel_RightSt$0"));
bounds.boundExactly(x5, x5_upper);

TupleSet x6_upper = factory.noneOf(1);
x6_upper.add(factory.tuple("PhotoRel_ChildRemovedSt$0"));
bounds.boundExactly(x6, x6_upper);

TupleSet x7_upper = factory.noneOf(1);
x7_upper.add(factory.tuple("PhotoRel_AllRemovedSt$0"));
bounds.boundExactly(x7, x7_upper);

TupleSet x8_upper = factory.noneOf(2);
x8_upper.add(factory.tuple("User$0").product(factory.tuple("Photo$0")));
x8_upper.add(factory.tuple("User$0").product(factory.tuple("Photo$1")));
x8_upper.add(factory.tuple("User$0").product(factory.tuple("Photo$2")));
x8_upper.add(factory.tuple("User$1").product(factory.tuple("Photo$0")));
x8_upper.add(factory.tuple("User$1").product(factory.tuple("Photo$1")));
x8_upper.add(factory.tuple("User$1").product(factory.tuple("Photo$2")));
x8_upper.add(factory.tuple("User$2").product(factory.tuple("Photo$0")));
x8_upper.add(factory.tuple("User$2").product(factory.tuple("Photo$1")));
x8_upper.add(factory.tuple("User$2").product(factory.tuple("Photo$2")));
bounds.bound(x8, x8_upper);

TupleSet x9_upper = factory.noneOf(2);
x9_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$0")));
x9_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$1")));
x9_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$2")));
x9_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$0")));
x9_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$1")));
x9_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$2")));
x9_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$0")));
x9_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$1")));
x9_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$2")));
bounds.bound(x9, x9_upper);

TupleSet x10_upper = factory.noneOf(1);
x10_upper.add(factory.tuple("User$0"));
x10_upper.add(factory.tuple("User$1"));
x10_upper.add(factory.tuple("User$2"));
bounds.bound(x10, x10_upper);

TupleSet x11_upper = factory.noneOf(1);
x11_upper.add(factory.tuple("Photo$0"));
x11_upper.add(factory.tuple("Photo$1"));
x11_upper.add(factory.tuple("Photo$2"));
bounds.bound(x11, x11_upper);

TupleSet x12_upper = factory.noneOf(2);
x12_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$0")));
x12_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$1")));
x12_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$2")));
x12_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$0")));
x12_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$1")));
x12_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$2")));
x12_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$0")));
x12_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$1")));
x12_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$2")));
bounds.bound(x12, x12_upper);

TupleSet x13_upper = factory.noneOf(1);
x13_upper.add(factory.tuple("User$0"));
x13_upper.add(factory.tuple("User$1"));
x13_upper.add(factory.tuple("User$2"));
bounds.bound(x13, x13_upper);

TupleSet x14_upper = factory.noneOf(1);
x14_upper.add(factory.tuple("User$0"));
x14_upper.add(factory.tuple("User$1"));
x14_upper.add(factory.tuple("User$2"));
bounds.bound(x14, x14_upper);

TupleSet x15_upper = factory.noneOf(1);
x15_upper.add(factory.tuple("Photo$0"));
x15_upper.add(factory.tuple("Photo$1"));
x15_upper.add(factory.tuple("Photo$2"));
bounds.bound(x15, x15_upper);

TupleSet x16_upper = factory.noneOf(2);
x16_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$0")));
x16_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$1")));
x16_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$2")));
x16_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$0")));
x16_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$1")));
x16_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$2")));
x16_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$0")));
x16_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$1")));
x16_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$2")));
bounds.bound(x16, x16_upper);

TupleSet x17_upper = factory.noneOf(1);
x17_upper.add(factory.tuple("User$0"));
x17_upper.add(factory.tuple("User$1"));
x17_upper.add(factory.tuple("User$2"));
bounds.bound(x17, x17_upper);

TupleSet x18_upper = factory.noneOf(1);
x18_upper.add(factory.tuple("Photo$0"));
x18_upper.add(factory.tuple("Photo$1"));
x18_upper.add(factory.tuple("Photo$2"));
bounds.bound(x18, x18_upper);

TupleSet x19_upper = factory.noneOf(2);
x19_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$0")));
x19_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$1")));
x19_upper.add(factory.tuple("Photo$0").product(factory.tuple("User$2")));
x19_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$0")));
x19_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$1")));
x19_upper.add(factory.tuple("Photo$1").product(factory.tuple("User$2")));
x19_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$0")));
x19_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$1")));
x19_upper.add(factory.tuple("Photo$2").product(factory.tuple("User$2")));
bounds.bound(x19, x19_upper);


Variable x23=Variable.unary("deleteUser_this");
Decls x22=x23.oneOf(x3);
Expression x25=x23.join(x8);
Formula x24=x25.in(x4);
Formula x21=x24.forAll(x22);
Expression x27=x8.join(Expression.UNIV);
Formula x26=x27.in(x3);
Variable x31=Variable.unary("deleteUser_this");
Decls x30=x31.oneOf(x4);
Expression x34=x31.join(x9);
Formula x33=x34.one();
Formula x35=x34.in(x3);
Formula x32=x33.and(x35);
Formula x29=x32.forAll(x30);
Expression x37=x9.join(Expression.UNIV);
Formula x36=x37.in(x4);
Expression x40=x5.product(x10);
Expression x39=x5.join(x40);
Formula x38=x39.in(x3);
Expression x44=x5.product(x11);
Expression x43=x5.join(x44);
Formula x42=x43.some();
Formula x45=x43.in(x4);
Formula x41=x42.and(x45);
Expression x48=x5.product(x12);
Expression x47=x5.join(x48);
Expression x50=x5.join(x44);
Expression x51=x5.join(x40);
Expression x49=x50.product(x51);
Formula x46=x47.in(x49);
Expression x54=x11.product(Expression.UNIV);
Expression x53=x54.intersection(x9);
Formula x52=x12.eq(x53);
Expression x57=x6.product(x13);
Expression x56=x6.join(x57);
Formula x55=x56.in(x3);
Expression x60=x6.product(x14);
Expression x59=x6.join(x60);
Formula x58=x59.in(x3);
Expression x64=x6.product(x15);
Expression x63=x6.join(x64);
Formula x62=x63.some();
Formula x65=x63.in(x4);
Formula x61=x62.and(x65);
Expression x70=x6.product(x16);
Expression x69=x6.join(x70);
Expression x72=x6.join(x64);
Expression x74=x6.join(x60);
Expression x75=x6.join(x57);
Expression x73=x74.difference(x75);
Expression x71=x72.product(x73);
Formula x68=x69.in(x71);
Variable x78=Variable.unary("v28");
Decls x77=x78.oneOf(x72);
Expression x80=x78.join(x69);
Expression x82=x6.join(x60);
Expression x83=x6.join(x57);
Expression x81=x82.difference(x83);
Formula x79=x80.in(x81);
Formula x76=x79.forAll(x77);
Formula x67=x68.and(x76);
Variable x86=Variable.unary("v29");
Decls x85=x86.oneOf(x73);
Expression x89=x69.join(x86);
Formula x88=x89.some();
Expression x91=x6.join(x64);
Formula x90=x89.in(x91);
Formula x87=x88.and(x90);
Formula x84=x87.forAll(x85);
Formula x66=x67.and(x84);
Expression x95=x15.product(Expression.UNIV);
Expression x94=x95.intersection(x9);
Formula x93=x16.eq(x94);
Expression x98=x14.difference(x13);
Expression x97=x98.join(x8);
Formula x96=x15.eq(x97);
Formula x92=x93.and(x96);
Expression x102=x7.product(x17);
Expression x101=x7.join(x102);
Formula x100=x101.some();
Formula x103=x101.in(x3);
Formula x99=x100.and(x103);
Expression x107=x7.product(x18);
Expression x106=x7.join(x107);
Formula x105=x106.some();
Formula x108=x106.in(x4);
Formula x104=x105.and(x108);
Expression x111=x7.product(x19);
Expression x110=x7.join(x111);
Expression x113=x7.join(x107);
Expression x114=x7.join(x102);
Expression x112=x113.product(x114);
Formula x109=x110.in(x112);
Expression x118=x18.product(Expression.UNIV);
Expression x117=x118.intersection(x9);
Formula x116=x19.eq(x117);
Expression x120=x17.join(x8);
Formula x119=x18.eq(x120);
Formula x115=x116.and(x119);
Expression x122=x9.transpose();
Formula x121=x8.eq(x122);
Variable x126=Variable.unary("deleteUser_everyUser");
Decls x125=x126.oneOf(x3);
Variable x128=Variable.unary("deleteUser_everyPhoto");
Decls x127=x128.oneOf(x4);
Variable x130=Variable.unary("deleteUser_s");
Decls x129=x130.oneOf(x5);
Decls x124=x125.and(x127).and(x129);
Expression x133=x130.join(x40);
Formula x132=x126.in(x133);
Expression x135=x130.join(x44);
Formula x134=x128.in(x135);
Formula x131=x132.and(x134);
Formula x123=x131.forAll(x124);
Variable x139=Variable.unary("deleteUser_before");
Decls x138=x139.oneOf(x5);
Variable x141=Variable.unary("deleteUser_process");
Decls x140=x141.oneOf(x6);
Variable x143=Variable.unary("deleteUser_after");
Decls x142=x143.oneOf(x7);
Decls x137=x138.and(x140).and(x142);
Expression x146=x141.join(x57);
Expression x148=x139.join(x40);
Expression x149=x143.join(x102);
Expression x147=x148.difference(x149);
Formula x145=x146.eq(x147);
Expression x151=x141.join(x60);
Expression x152=x139.join(x40);
Formula x150=x151.eq(x152);
Formula x144=x145.and(x150);
Formula x136=x144.forAll(x137);
Variable x157=Variable.unary("deleteUser_user");
Decls x156=x157.oneOf(x3);
Variable x159=Variable.unary("deleteUser_rightSt");
Decls x158=x159.oneOf(x5);
Variable x161=Variable.unary("deleteUser_childRemoveSt");
Decls x160=x161.oneOf(x6);
Variable x163=Variable.unary("deleteUser_allRemovedSt");
Decls x162=x163.oneOf(x7);
Decls x155=x156.and(x158).and(x160).and(x162);
Expression x168=x159.join(x40);
Expression x169=x163.join(x102);
Expression x167=x168.difference(x169);
Formula x166=x157.eq(x167);
Formula x165=x166.not();
Expression x173=x159.join(x48);
Expression x174=x161.join(x70);
Expression x172=x173.difference(x174);
Expression x178=x161.join(x57);
Expression x177=x178.join(x8);
Expression x176=x177.product(Expression.UNIV);
Expression x175=x176.intersection(x9);
Formula x171=x172.eq(x175);
Expression x181=x159.join(x48);
Expression x182=x163.join(x111);
Expression x180=x181.difference(x182);
Expression x185=x157.join(x8);
Expression x184=x185.product(Expression.UNIV);
Expression x183=x184.intersection(x9);
Formula x179=x180.eq(x183);
Formula x170=x171.and(x179);
Formula x164=x165.or(x170);
Formula x154=x164.forAll(x155);
Formula x153=x154.not();
Formula x186=x0.eq(x0);
Formula x187=x1.eq(x1);
Formula x188=x2.eq(x2);
Formula x189=x3.eq(x3);
Formula x190=x4.eq(x4);
Formula x191=x5.eq(x5);
Formula x192=x6.eq(x6);
Formula x193=x7.eq(x7);
Formula x194=x8.eq(x8);
Formula x195=x9.eq(x9);
Formula x196=x10.eq(x10);
Formula x197=x11.eq(x11);
Formula x198=x12.eq(x12);
Formula x199=x13.eq(x13);
Formula x200=x14.eq(x14);
Formula x201=x15.eq(x15);
Formula x202=x16.eq(x16);
Formula x203=x17.eq(x17);
Formula x204=x18.eq(x18);
Formula x205=x19.eq(x19);
Formula x20=Formula.compose(FormulaOperator.AND, x21, x26, x29, x36, x38, x41, x46, x52, x55, x58, x61, x66, x92, x99, x104, x109, x115, x121, x123, x136, x153, x186, x187, x188, x189, x190, x191, x192, x193, x194, x195, x196, x197, x198, x199, x200, x201, x202, x203, x204, x205);

Solver solver = new Solver();
solver.options().setSolver(SATFactory.DefaultSAT4J);
solver.options().setBitwidth(1);
solver.options().setFlatten(false);
solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
solver.options().setSymmetryBreaking(20);
solver.options().setSkolemDepth(0);
System.out.println("Solving...");
System.out.flush();
Solution sol = solver.solve(x20,bounds);
System.out.println(sol.toString());
}}
