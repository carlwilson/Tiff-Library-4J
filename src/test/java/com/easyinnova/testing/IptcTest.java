/**
 * <h1>Iptc.java</h1>
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version; or, at your choice, under the terms of the
 * Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License and the Mozilla Public License for more details.
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License and the Mozilla Public License
 * along with this program. If not, see <a
 * href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a> and at <a
 * href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> .
 * </p>
 * <p>
 * NB: for the © statement, include Easy Innova SL or other company/Person contributing the code.
 * </p>
 * <p>
 * © 2015 Easy Innova, SL
 * </p>
 *
 * @author Víctor Muñoz Solà
 * @version 1.0
 * @since 14/7/2015
 *
 */
package com.easyinnova.testing;

import static org.junit.Assert.assertEquals;

import com.easyinnova.tiff.model.Metadata;
import com.easyinnova.tiff.model.TagValue;
import com.easyinnova.tiff.model.TiffDocument;
import com.easyinnova.tiff.model.types.IFD;
import com.easyinnova.tiff.reader.TiffReader;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class Iptc.
 */
public class IptcTest {

  /** The tr. */
  TiffReader tr;

  /** The to. */
  TiffDocument to;

  /** The tv. */
  TagValue tv;

  /** The ifd. */
  IFD ifd;

  /** The result. */
  int result;

  /**
   * Pre test.
   */
  @Before
  public void PreTest() {
    boolean ok = true;
    try {
      tr = new TiffReader();
    } catch (Exception e) {
      ok = false;
    }
    assertEquals(ok, true);
  }

  /**
   * Test.
   */
  @Test
  public void test1() {
    result = tr.readFile("src\\test\\resources\\Small\\Bilevel.tif");
    to = tr.getModel();
    ifd = (IFD) to.getFirstIFD();
    tv = ifd.getTag("IPTC");
    assertEquals(true, tv.getValue().get(0).containsMetadata());
    try {
      Metadata m = tv.getValue().get(0).createMetadata();
      assertEquals("13:57:15 00:00", m.getMetadataObject("TimeCreated").getObjectList().get(0)
          .toString());
      assertEquals("0", m.getMetadataObject("RecordVersion").getObjectList().get(0).toString());
      assertEquals("24/05/2010", m.getMetadataObject("DateCreated").getObjectList().get(0)
          .toString());
    } catch (Exception e) {
      assertEquals(0, 1);
    }
  }

  /**
   * Test.
   */
  @Test
  public void test2() {
    result = tr.readFile("src\\test\\resources\\IPTC\\IPTC.tif");
    to = tr.getModel();
    ifd = (IFD) to.getFirstIFD();
    tv = ifd.getTag("IPTC");
    assertEquals(true, tv.getValue().get(0).containsMetadata());
    try {
      Metadata m = tv.getValue().get(0).createMetadata();
      assertEquals(2, m.getMetadataObject("Keywords").getObjectList().size());
      String k1 = m.getMetadataObject("Keywords").getObjectList().get(0).toString();
      String k2 = m.getMetadataObject("Keywords").getObjectList().get(1).toString();
      assertEquals(true, k1.equals("sample") || k2.equals("sample"));
      assertEquals(true, k1.equals("gray") || k2.equals("gray"));
      assertEquals("Spain", m.getMetadataObject("CountryName").getObjectList().get(0).toString());
      assertEquals("Parc Tecnologic", m.getMetadataObject("SubLocation").getObjectList().get(0)
          .toString());
      assertEquals("04/06/2015", m.getMetadataObject("DateCreated").getObjectList().get(0)
          .toString());
      assertEquals("Girona", m.getMetadataObject("City").getObjectList().get(0).toString());
      assertEquals("This is the description of a sample image", m.getMetadataObject("Caption")
          .getObjectList().get(0).toString());
      assertEquals("Girona", m.getMetadataObject("ProvinceState").getObjectList().get(0).toString());
      assertEquals("Gray image", m.getMetadataObject("ObjectName").getObjectList().get(0)
          .toString());
      assertEquals("00:00:00 00:00", m.getMetadataObject("TimeCreated").getObjectList().get(0)
          .toString());
      assertEquals("Antonio Lopez", m.getMetadataObject("Byline").getObjectList().get(0).toString());
      assertEquals("53054", m.getMetadataObject("RecordVersion").getObjectList().get(0).toString());
      assertEquals("Developer", m.getMetadataObject("BylineTitle").getObjectList().get(0)
          .toString());
      assertEquals("Head of the photo", m.getMetadataObject("Headline").getObjectList().get(0)
          .toString());
      assertEquals("ES", m.getMetadataObject("CountryCode").getObjectList().get(0)
          .toString());
      assertEquals("Antonio Lopez", m.getMetadataObject("Writer").getObjectList().get(0)
          .toString());
    } catch (Exception e) {
      assertEquals(0, 1);
    }
  }
}
