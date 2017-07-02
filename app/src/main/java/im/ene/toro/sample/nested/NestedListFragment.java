/*
 * Copyright (c) 2017 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.ene.toro.sample.nested;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import im.ene.toro.sample.R;
import im.ene.toro.sample.common.BaseFragment;
import im.ene.toro.widget.Container;

/**
 * @author eneim (7/1/17).
 *
 *         A list of content that contains a {@link Container} as one of its child. We gonna use a
 *         {@link PagerSnapHelper} to mimic a Pager-inside-RecyclerView. Other contents will be
 *         normal text to preseve the performance and also to not make user confused.
 */

@SuppressWarnings("unused") public class NestedListFragment extends BaseFragment {

  public static NestedListFragment newInstance() {
    Bundle args = new Bundle();
    NestedListFragment fragment = new NestedListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle bundle) {
    return inflater.inflate(R.layout.fragment_basic, container, false);
  }

  @BindView(R.id.player_container) Container container;
  LinearLayoutManager layoutManager;
  NestedListAdapter adapter;

  @Override public void onViewCreated(View view, @Nullable Bundle bundle) {
    super.onViewCreated(view, bundle);
    layoutManager = new LinearLayoutManager(getContext());
    container.setLayoutManager(layoutManager);
    layoutManager.setItemPrefetchEnabled(true);
    adapter = new NestedListAdapter();
    container.setAdapter(adapter);
    container.setPlayerStateManager(adapter);
  }

  @Override public void onDestroy() {
    layoutManager = null;
    adapter = null;
    super.onDestroy();
  }
}